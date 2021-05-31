package alkemy.challenge.Challenge.Alkemy.exception;
//An exception to manage users that are not registered ind database
public class UserDoesNotExistException extends Exception {
    private String message;

    public UserDoesNotExistException(String message) {
        this.message = message;
    }

}
