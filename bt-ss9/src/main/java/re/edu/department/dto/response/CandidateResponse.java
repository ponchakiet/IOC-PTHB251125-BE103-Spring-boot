package re.edu.department.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResponse {
    private Long id;
    private String name;
    private String email;
    private String cvUrl;
}
