package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
