package alkemy.challenge.Challenge.Alkemy.exception;

//An exception to manage posts that doesn't exist or for unusual attempts
public class NotPostFoundException extends RuntimeException {
    private String message;

    public NotPostFoundException(String message) {
        this.message = message;

    }
}
