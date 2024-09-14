package net.loobo.medicare.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Builder
@Entity
@Table(name="prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(generator = "string-id-generator")
    @GenericGenerator(name = "string-id-generator", strategy = "net.loobo.medicare.db.StringIdGenerator")

    private String id;

    // Mapping the foreign key to Person
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    private String medication;
    private String doctor;
    private String pharmacy;
    private String dosage;
    private int refills;
    private double dose;
    private double frequency;
    private String timeUnit;
    private int totalAmount;
    private String unit;
    private LocalDate prescriptionDate;
    private LocalDate firstFillDate;
    private LocalDate lastRefillDate;
    private LocalDate finishDate;
    private LocalDateTime createTime;
    private LocalDateTime notificationSentTime;

    @PrePersist
    public void updateLastRefillDate() {
        if (firstFillDate == null) {
            return;
        }

        var frequencyUnitDays = switch (timeUnit) {
            case "YEAR" -> 365;
            case "MONTH" -> 30;
            case "DAY" -> 1;
            default -> TimeUnit.valueOf(timeUnit).toDays(1);
        };

        var dailyFrequency = frequency/frequencyUnitDays;
        var days = (int)(totalAmount/dose/dailyFrequency);

        // to get new prescription at the last refill time
        lastRefillDate = firstFillDate.plusDays(days * (refills));
    }

}
