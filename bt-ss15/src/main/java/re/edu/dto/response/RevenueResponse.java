package re.edu.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RevenueResponse {
    private String type;
    private String period;
    private BigDecimal revenue;
}
