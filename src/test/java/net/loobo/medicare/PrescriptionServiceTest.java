package net.loobo.medicare;

import net.loobo.medicare.db.model.Person;
import net.loobo.medicare.db.model.Prescription;
import net.loobo.medicare.db.repository.PersonRepository;
import net.loobo.medicare.db.repository.PrescriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class PrescriptionServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Test
    public void addPersonTest() {
        personRepository.save(
                Person.builder()
                        .firstName("Juejun")
                        .lastName("Xiao")
                        .build()
        );
    }

    @Test
    public void addPrescriptionTest() {
        prescriptionRepository.save(
                Prescription.builder()
                        .person(personRepository.findByFirstName("Zhongxin").get())
                        .doctor("Sadik, Zaki")
                        .pharmacy("Costco")
                        .medication("Pms-Finasteride")
                        .totalAmount(90)
                        .refills(0)
                        .dose(1)
                        .frequency(1)
                        .timeUnit("DAY")
                        .unit("TAB")
                        .prescriptionDate(LocalDate.of(2024, 6,8))
                        .firstFillDate(LocalDate.of(2024,6,8))
                        .createTime(LocalDateTime.now())
                        .dosage("TAKE 1 TABLET DAILY")
                        .build()
        );
    }


}
