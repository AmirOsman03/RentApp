package mk.finki.ukim.mk.rent_v2.model.exceptions;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() {
        super("Invalid user ID");
    }
}
