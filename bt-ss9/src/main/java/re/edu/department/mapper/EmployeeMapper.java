package re.edu.department.mapper;

import org.springframework.stereotype.Component;
import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.request.EmployeeCreateDTO;
import re.edu.department.dto.response.EmployeeResponse;
import re.edu.department.model.Department;
import re.edu.department.model.Employee;

@Component
public class EmployeeMapper {
    public static EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setSalary(employee.getSalary());
        response.setDepartmentId(employee.getDepartment().getId());
        return response;

    }

    public static Employee mapToEntity(EmployeeCreateDTO dto, Department department){
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);
        return employee;
    }
}
