package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.model.dto.response.DailyExportResponse;
import re.edu.model.dto.response.TopExportResponse;
import re.edu.model.entity.Transaction;
import re.edu.model.entity.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
        SELECT new re.edu.model.dto.response.DailyExportResponse(
            t.supply.id,
            t.supply.name,
            CAST(SUM(t.amount) AS integer)
        )
        FROM Transaction t
        WHERE t.type = :type
          AND t.createdAt >= :startOfDay
          AND t.createdAt < :endOfDay
        GROUP BY t.supply.id, t.supply.name
    """)
    List<DailyExportResponse> findDailyExportStatistics(
            @Param("type") TransactionType type,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    @Query("""
        SELECT new re.edu.model.dto.response.TopExportResponse(
            t.supply.id,
            t.supply.name,
            SUM(t.amount)
        )
        FROM Transaction t
        WHERE t.type = :type
        GROUP BY t.supply.id, t.supply.name
        ORDER BY SUM(t.amount) DESC
    """)
    List<TopExportResponse> findTopExportSupply(@Param("type") TransactionType type);
    boolean existsByType(TransactionType type);
}
