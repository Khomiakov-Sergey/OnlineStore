package by.it.academy.ishop.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception class for user authentication, if something goes wrong.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
public class UserAuthenticationException extends AuthenticationException {

    public UserAuthenticationException(String message) {
        super(message);
    }
}
