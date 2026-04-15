package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {}
