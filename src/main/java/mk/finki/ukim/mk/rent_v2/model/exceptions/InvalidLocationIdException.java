package mk.finki.ukim.mk.rent_v2.model.exceptions;

public class InvalidLocationIdException extends RuntimeException {
    public InvalidLocationIdException() {
        super("Invalid location ID");
    }
}
