package mk.finki.ukim.mk.rent_v2.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Passwords do not match");
    }
}
