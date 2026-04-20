package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.model.entity.Supply;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    List<Supply> findAllByIsDeletedFalse();
    Optional<Supply> findByIdAndIsDeletedFalse(Long id);
    @Query("SELECT s FROM Supply s WHERE s.isDeleted = false AND LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Supply> findByNameContainingIgnoreCaseAndIsDeletedFalse(@Param("name") String name);
}
