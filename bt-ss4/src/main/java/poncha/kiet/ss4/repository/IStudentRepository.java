package poncha.kiet.ss4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poncha.kiet.ss4.entity.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long> {
}
