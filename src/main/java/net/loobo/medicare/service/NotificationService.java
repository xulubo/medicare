package net.loobo.medicare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.loobo.medicare.db.model.Prescription;
import net.loobo.medicare.db.repository.PrescriptionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final PrescriptionRepository prescriptionRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 * * * * *")
    public void checkAndNotify() {
        var zeroRefillPrescriptionList = zeroRefillPrescriptions();
        if (zeroRefillPrescriptionList.isEmpty()) {
            log.info("no new prescription request needed!");
            return;
        }

        emailService.sendEmail("xulubo@gmail.com", "time for new prescription",

                zeroRefillPrescriptionList.stream()
                        .map(p->p.getMedication())
                        .collect(Collectors.joining(","))
                );

        zeroRefillPrescriptionList.forEach(p->p.setNotificationSentTime(LocalDateTime.now()));

        prescriptionRepository.saveAll(zeroRefillPrescriptionList);
    }

    private List<Prescription> zeroRefillPrescriptions() {
        return prescriptionRepository.findAll()
                .stream()
                .filter(p->p.getNotificationSentTime() == null)
                .filter(p->p.getLastRefillDate() != null)
                .filter(p->p.getLastRefillDate().isBefore(LocalDate.now()))
                .toList();
    }
}
