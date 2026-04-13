package re.edu.service.implement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.edu.dto.request.BookCreateRequest;
import re.edu.dto.request.BookUpdateStock;
import re.edu.dto.response.BookResponse;
import re.edu.exception.ResourceNotFoundException;
import re.edu.model.Book;
import re.edu.mapper.BookMapper;
import re.edu.repositories.BookRepository;
import re.edu.service.IBookService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public BookResponse createBook(BookCreateRequest request) {
        try {
            MultipartFile file = request.getCoverImage();

            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            File destination = new File(UPLOAD_DIR + fileName);
            file.transferTo(destination);

            Book book = bookMapper.mapToEntity(request, fileName);
            Book saved = bookRepository.save(book);

            return bookMapper.mapToResponse(saved);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public BookResponse updateBook(Long id, BookUpdateStock dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setStock(dto.getStock());

        Book updated = bookRepository.save(book);

        return bookMapper.mapToResponse(updated);
    }
}
