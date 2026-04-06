package poncha.kiet.btss6.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketSummaryResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
}