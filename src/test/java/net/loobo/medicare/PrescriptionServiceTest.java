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
                        .person(personRepository.findByFirstName("Juejun").get())
                        .doctor("Chanhing Ho")
                        .pharmacy("Costco")
                        .medication("Act-Pramipexole")
                        .totalAmount(720)
                        .refills(2)
                        .dose(2)
                        .frequency(4)
                        .timeUnit("DAY")
                        .unit("TAB")
                        .prescriptionDate(LocalDate.of(2024, 7,29))
                        .firstFillDate(LocalDate.of(2024,7,29))
                        .createTime(LocalDateTime.now())
                        .dosage("TAKE 1/2 TABLET AT BEDTIME")
                        .build()
        );
    }


}
