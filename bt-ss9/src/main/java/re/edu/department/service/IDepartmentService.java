package re.edu.department.service;

import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.response.DepartmentResponse;

public interface IDepartmentService {
    DepartmentResponse createDepartment(DepartmentDTO dto);

}
