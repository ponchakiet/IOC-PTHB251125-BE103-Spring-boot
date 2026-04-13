package re.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    boolean existsByEmail(String email);

}
