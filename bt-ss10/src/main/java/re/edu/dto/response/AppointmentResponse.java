package re.edu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {
    private Long id;
    private String appointmentTime;
    private String reason;
}
