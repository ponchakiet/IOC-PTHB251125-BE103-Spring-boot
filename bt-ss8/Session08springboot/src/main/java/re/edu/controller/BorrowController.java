package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.BorrowCreate;
import re.edu.dto.response.BorrowResponse;
import re.edu.model.*;
import re.edu.repositories.BorrowRepository;
import re.edu.service.IBorrowService;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final IBorrowService borrowService;
    private final BorrowRepository borrowRepository;

    @PostMapping
    public ResponseEntity<?> createBorrow(@Valid @RequestBody BorrowCreate request) {

        BorrowTicket ticket = BorrowTicket.builder()
                .username(request.getUsername())
                .bookId(request.getBookId())
                .status(BorrowStatus.BORROWING)
                .build();

        borrowRepository.save(ticket);

        return ResponseEntity.ok("Tạo phiếu mượn thành công");
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<BorrowResponse> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }
}
