package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
