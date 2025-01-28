package mk.finki.ukim.mk.rent_v2.model.exceptions;

public class InvalidCategoryIdException extends RuntimeException {
    public InvalidCategoryIdException() {
        super("Invalid category ID.");
    }
}
