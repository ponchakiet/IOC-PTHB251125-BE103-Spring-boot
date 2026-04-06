package poncha.kiet.btss6.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TicketResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
}
