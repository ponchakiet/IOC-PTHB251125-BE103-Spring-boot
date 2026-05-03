package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.entity.Product;
import re.edu.entity.Review;
import re.edu.entity.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

    boolean existsByUserAndProduct(User user, Product product);

    // Check customer actually bought the product (any DELIVERED order containing it)
    @Query("""
            SELECT COUNT(oi) > 0
            FROM OrderItem oi
            WHERE oi.order.user = :user
              AND oi.product = :product
              AND oi.order.status = 'DELIVERED'
            """)
    boolean hasPurchasedProduct(@Param("user") User user, @Param("product") Product product);
}
