package re.edu.dto.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer stock;
    private String coverUrl;
}
