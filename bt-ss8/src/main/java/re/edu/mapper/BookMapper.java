package re.edu.mapper;

import org.springframework.stereotype.Component;
import re.edu.dto.request.BookCreateRequest;
import re.edu.dto.response.BookResponse;
import re.edu.model.Book;

@Component
public class BookMapper {
    public BookResponse mapToResponse(Book entity){
        BookResponse response = new BookResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setAuthor(entity.getAuthor());
        response.setStock(entity.getStock());
        response.setCoverUrl("/uploads/" + entity.getCoverUrl());
        return response;
    }
    public Book mapToEntity(BookCreateRequest request, String filename){
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setStock(request.getStock());
        book.setCoverUrl(filename);
        return book;
    }
}
