package re.edu.department.mapper;

import org.springframework.stereotype.Component;
import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.response.DepartmentResponse;
import re.edu.department.model.Department;
@Component
public class DepartmentMapper {
    public static DepartmentResponse mapToResponse(Department entity){
        DepartmentResponse response = new DepartmentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        return response;
    }
    public static Department mapToEntity(DepartmentDTO dto){
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }

}
