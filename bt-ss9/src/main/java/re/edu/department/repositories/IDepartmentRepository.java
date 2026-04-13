package re.edu.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.department.model.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}
