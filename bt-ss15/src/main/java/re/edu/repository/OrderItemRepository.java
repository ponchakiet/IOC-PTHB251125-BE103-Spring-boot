package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
