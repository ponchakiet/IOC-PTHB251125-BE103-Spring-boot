package re.edu.mapper;

import org.springframework.stereotype.Component;
import re.edu.dto.request.BorrowCreate;
import re.edu.dto.response.BorrowResponse;
import re.edu.model.BorrowStatus;
import re.edu.model.BorrowTicket;

@Component
public class BorrowMapper {

    // Request -> Entity
    public BorrowTicket toEntity(BorrowCreate dto) {
        if (dto == null) return null;

        BorrowTicket ticket = new BorrowTicket();
        ticket.setUsername(dto.getUsername());
        ticket.setBookId(dto.getBookId());
        ticket.setStatus(BorrowStatus.BORROWING);

        return ticket;
    }

    public static BorrowResponse toResponse(BorrowTicket ticket) {
        return BorrowResponse.builder()
                .id(ticket.getId())
                .username(ticket.getUsername())
                .bookId(ticket.getBookId())
                .status(ticket.getStatus())
                .returnDate(ticket.getReturnDate())
                .build();
    }
}
