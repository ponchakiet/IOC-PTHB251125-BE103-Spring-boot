package re.edu.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.department.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
