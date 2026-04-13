package re.edu.department.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.response.DepartmentResponse;
import re.edu.department.mapper.DepartmentMapper;
import re.edu.department.model.Department;
import re.edu.department.repositories.IDepartmentRepository;
import re.edu.department.service.IDepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;


    @Override
    public DepartmentResponse createDepartment(DepartmentDTO dto) {
        Department entity = DepartmentMapper.mapToEntity(dto);

        if (departmentRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Department tên " + dto.getName() + " đã tồn tại");
        }

        entity = departmentRepository.save(entity);
        return DepartmentMapper.mapToResponse(entity);
    }
}
