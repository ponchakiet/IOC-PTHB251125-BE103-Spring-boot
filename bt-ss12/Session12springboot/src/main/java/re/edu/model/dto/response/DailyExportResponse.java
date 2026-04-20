package re.edu.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyExportResponse {

    private Long supplyId;
    private String supplyName;
    private Integer totalExportedToday;
}
