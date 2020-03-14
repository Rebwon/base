package ko.maeng.oop.vending.domain.exception;

public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException() {
    }

    public BeverageNotFoundException(String message) {
        super(message);
    }

    public BeverageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
