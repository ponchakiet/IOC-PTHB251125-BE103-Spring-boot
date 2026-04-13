package re.edu.department.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Long salary;
    private Long departmentId;
}
