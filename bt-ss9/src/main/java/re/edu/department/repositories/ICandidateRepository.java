package re.edu.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.department.model.Candidate;

public interface ICandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);
}
