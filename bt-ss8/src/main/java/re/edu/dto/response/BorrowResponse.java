package re.edu.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import re.edu.model.BorrowStatus;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class BorrowResponse {
    private Long id;
    private String username;
    private Long bookId;
    private BorrowStatus status;
    private LocalDate returnDate;
}
