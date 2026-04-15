package re.edu.service;

import re.edu.dto.request.BorrowCreate;
import re.edu.dto.response.BorrowResponse;

public interface IBorrowService {
    BorrowResponse createBorrow(BorrowCreate dto);

    BorrowResponse returnBook(Long ticketId);

}
