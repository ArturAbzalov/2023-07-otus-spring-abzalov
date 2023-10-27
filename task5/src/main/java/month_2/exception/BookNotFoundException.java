package month_2.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String format) {
        super(format);
    }
}
