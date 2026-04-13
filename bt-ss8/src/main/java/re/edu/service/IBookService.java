package re.edu.service;

import re.edu.dto.request.BookCreateRequest;
import re.edu.dto.request.BookUpdateStock;
import re.edu.dto.response.BookResponse;

public interface IBookService {
    BookResponse createBook (BookCreateRequest request);
    BookResponse updateBook(Long id, BookUpdateStock dto);
}
