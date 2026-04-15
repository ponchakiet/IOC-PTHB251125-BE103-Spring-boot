package re.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.model.BorrowTicket;

public interface BorrowRepository extends JpaRepository<BorrowTicket, Long> {
}
