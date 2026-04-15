package re.edu.exception;

public class BookAlreadyReturnedException extends RuntimeException {
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
