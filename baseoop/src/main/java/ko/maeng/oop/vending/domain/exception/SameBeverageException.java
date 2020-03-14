package ko.maeng.oop.vending.domain.exception;

public class SameBeverageException extends RuntimeException {
    public SameBeverageException() {
    }

    public SameBeverageException(String message) {
        super(message);
    }

    public SameBeverageException(String message, Throwable cause) {
        super(message, cause);
    }
}
