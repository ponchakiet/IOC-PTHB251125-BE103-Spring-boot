package poncha.kiet.btss7.repository;

import poncha.kiet.btss7.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidateRepository extends JpaRepository<Candidate,Long> {
}
