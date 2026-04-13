package re.edu.department.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.request.EmployeeCreateDTO;
import re.edu.department.dto.response.DepartmentResponse;
import re.edu.department.dto.response.EmployeeResponse;
import re.edu.department.exception.DuplicateResourceException;
import re.edu.department.exception.InvalidFileException;
import re.edu.department.exception.ResourceNotFoundException;
import re.edu.department.mapper.DepartmentMapper;
import re.edu.department.mapper.EmployeeMapper;
import re.edu.department.model.Department;
import re.edu.department.model.Employee;
import re.edu.department.repositories.IDepartmentRepository;
import re.edu.department.repositories.IEmployeeRepository;
import re.edu.department.service.IEmployeeService;
import re.edu.department.validation.FileValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;
    @Override
    public EmployeeResponse createEmployee(EmployeeCreateDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại"));

        Employee employee = EmployeeMapper.mapToEntity(dto, department);

        if(employeeRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Số điện thoai đã tồn tại");
        }
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }
        employee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToResponse(employee);
    }
    @Override
    public EmployeeResponse updateAvatar(Long id, MultipartFile file) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhân viên không tồn tại"));

        FileValidator.validateImage(file);

        try {
            String uploadDir = "uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String originalName = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "_" + originalName;

            Path path = Paths.get(uploadDir + newFileName);
            Files.write(path, file.getBytes());

            String fileUrl = "/uploads/" + newFileName;

            employee.setAvatarUrl(fileUrl);
            employeeRepository.save(employee);

            return EmployeeMapper.mapToResponse(employee);

        } catch (IOException e) {
            throw new RuntimeException("Upload file thất bại");
        }
    }
}
