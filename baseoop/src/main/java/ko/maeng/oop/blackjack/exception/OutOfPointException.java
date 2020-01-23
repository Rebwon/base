package ko.maeng.oop.blackjack.exception;

public class OutOfPointException extends RuntimeException {
    public OutOfPointException(String message) {
        super(message);
    }
}
