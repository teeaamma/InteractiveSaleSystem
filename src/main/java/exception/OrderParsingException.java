package exception;

public class OrderParsingException extends RuntimeException {
    public OrderParsingException(String message) {
        super(message);
    }
}
