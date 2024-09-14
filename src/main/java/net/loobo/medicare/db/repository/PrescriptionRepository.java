package net.loobo.medicare.db.repository;

import net.loobo.medicare.db.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
}