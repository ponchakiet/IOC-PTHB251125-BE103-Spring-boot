package re.edu.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateStock {
    @NotNull
    @Min(0)
    private Integer stock;
}
