package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.model.Patient;

import java.util.List;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.name LIKE %:name%")
    List<Patient> searchByName(@Param("name") String name);
}
