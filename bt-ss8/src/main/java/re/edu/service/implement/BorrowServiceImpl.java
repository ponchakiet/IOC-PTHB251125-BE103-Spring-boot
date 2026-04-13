package re.edu.service.implement;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.request.BorrowCreate;
import re.edu.dto.response.BorrowResponse;
import re.edu.model.*;
import re.edu.exception.BookAlreadyReturnedException;
import re.edu.exception.ResourceNotFoundException;
import re.edu.mapper.BorrowMapper;
import re.edu.repositories.*;
import re.edu.service.IBorrowService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements IBorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BorrowMapper borrowMapper;

    @Override
    public BorrowResponse createBorrow(BorrowCreate dto) {

        BorrowTicket ticket = borrowMapper.toEntity(dto);

        BorrowTicket saved = borrowRepository.save(ticket);

        return borrowMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BorrowResponse returnBook(Long ticketId) {

        BorrowTicket ticket = borrowRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Phiếu mượn không tồn tại"));

        if (ticket.getStatus() == BorrowStatus.RETURNED) {
            throw new BookAlreadyReturnedException("Sách này đã được trả rồi");
        }

        ticket.setStatus(BorrowStatus.RETURNED);
        ticket.setReturnDate(LocalDate.now());

        Book book = bookRepository.findById(ticket.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Sách không tồn tại"));

        book.setStock(book.getStock() + 1);

        return BorrowMapper.toResponse(ticket);
    }
}
