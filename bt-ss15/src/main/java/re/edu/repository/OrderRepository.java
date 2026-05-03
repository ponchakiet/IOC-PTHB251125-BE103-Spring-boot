package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.entity.Order;
import re.edu.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    @Query("""
            SELECT COALESCE(SUM(o.totalMoney), 0)
            FROM Order o
            WHERE o.status = 'DELIVERED'
              AND FUNCTION('DATE', o.createdDate) = FUNCTION('DATE', CURRENT_DATE)
            """)
    BigDecimal revenueToday();

    @Query("""
            SELECT COALESCE(SUM(o.totalMoney), 0)
            FROM Order o
            WHERE o.status = 'DELIVERED'
              AND YEAR(o.createdDate) = :year
              AND MONTH(o.createdDate) = :month
            """)
    BigDecimal revenueByMonth(@Param("year") int year, @Param("month") int month);

    @Query("""
            SELECT COALESCE(SUM(o.totalMoney), 0)
            FROM Order o
            WHERE o.status = 'DELIVERED'
              AND YEAR(o.createdDate) = :year
            """)
    BigDecimal revenueByYear(@Param("year") int year);
}
