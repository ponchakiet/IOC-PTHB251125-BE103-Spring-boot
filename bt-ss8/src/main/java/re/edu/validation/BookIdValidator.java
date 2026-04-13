package re.edu.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import re.edu.repositories.BookRepository;

@Component
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return bookRepository.existsById(value);
    }
}
