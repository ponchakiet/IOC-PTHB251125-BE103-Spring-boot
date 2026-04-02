package poncha.kiet.ss4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poncha.kiet.ss4.entity.Course;
import poncha.kiet.ss4.entity.CourseStatus;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
    @Query(value = "SELECT c from Course c where c.status = :status")
    public Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);
}
