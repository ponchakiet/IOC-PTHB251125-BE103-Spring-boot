package re.edu.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta {
    private long total;
    private int page;
    private int size;
}
