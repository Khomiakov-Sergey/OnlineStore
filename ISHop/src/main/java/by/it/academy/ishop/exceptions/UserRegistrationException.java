package by.it.academy.ishop.exceptions;

/**
 * Exception class for user registration, if user has already been created.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
public class UserRegistrationException extends IllegalArgumentException{

    private static final String INCORRECT_REGISTRATION = "User with such login or email have already been registered";

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException() {
        super(INCORRECT_REGISTRATION);
    }
}
