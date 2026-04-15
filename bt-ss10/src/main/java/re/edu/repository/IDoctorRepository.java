package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.entity.Doctor;

import java.util.Optional;
@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
  Optional<Doctor> findByEmail(String email);
  boolean existsByEmail(String email);

}
