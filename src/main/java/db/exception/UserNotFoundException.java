package db.exception;

/**
 * Created by landschoot on 29/04/17.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super("User not found");
    }
}
