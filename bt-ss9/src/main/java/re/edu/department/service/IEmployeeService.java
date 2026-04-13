package re.edu.department.service;

import org.springframework.web.multipart.MultipartFile;
import re.edu.department.dto.request.EmployeeCreateDTO;
import re.edu.department.dto.response.EmployeeResponse;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateDTO dto);
    EmployeeResponse updateAvatar(Long id, MultipartFile file);
}
