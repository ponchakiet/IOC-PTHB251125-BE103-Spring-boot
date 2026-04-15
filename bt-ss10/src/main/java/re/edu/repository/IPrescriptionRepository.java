package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Prescription;

import java.util.Optional;

public interface IPrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByIdAndPatientId(Long id, Long patientId);
}
