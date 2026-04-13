package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.BookCreateRequest;
import re.edu.dto.request.BookUpdateStock;
import re.edu.dto.response.BookResponse;
import re.edu.service.IBookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(
            @Valid @ModelAttribute BookCreateRequest request
    ) {
        try {
            BookResponse response = bookService.createBook(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStock dto
    ) {
        BookResponse response = bookService.updateBook(id, dto);
        return ResponseEntity.ok(response);
    }
}
