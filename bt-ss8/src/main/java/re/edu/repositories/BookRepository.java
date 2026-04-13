package re.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
