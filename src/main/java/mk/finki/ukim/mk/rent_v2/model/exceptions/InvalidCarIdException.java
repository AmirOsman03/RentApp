package mk.finki.ukim.mk.rent_v2.model.exceptions;

public class InvalidCarIdException extends RuntimeException {
    public InvalidCarIdException() {
        super("Invalid car ID");
    }
}
