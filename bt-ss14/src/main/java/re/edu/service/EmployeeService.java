package re.edu.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.EmployeeCreateDTO;
import re.edu.model.Employee;
import re.edu.repository.EmployeeRepository;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Cloudinary cloudinary;

    public Employee create(EmployeeCreateDTO dto) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(
                dto.getAvatarFile().getBytes(),
                ObjectUtils.asMap("folder", "employees")
        );
        String avatarUrl = (String) uploadResult.get("secure_url");

        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setAvatarUrl(avatarUrl);

        return employeeRepository.save(employee);
    }
}
