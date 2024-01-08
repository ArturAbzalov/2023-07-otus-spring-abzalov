package month_2.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String format) {
        super(format);
    }
}
