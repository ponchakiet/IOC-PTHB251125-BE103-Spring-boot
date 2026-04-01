package poncha.kiet.ss4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poncha.kiet.ss4.entity.StudentEnrollment;

@Repository
public interface IStudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
}
