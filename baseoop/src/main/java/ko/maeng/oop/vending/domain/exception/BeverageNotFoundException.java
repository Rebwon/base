package ko.maeng.oop.vending.domain.exception;

public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException(String message) {
        super(message);
    }
}
