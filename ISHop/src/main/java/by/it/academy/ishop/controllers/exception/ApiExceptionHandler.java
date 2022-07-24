package by.it.academy.ishop.controllers.exception;

import by.it.academy.ishop.dtos.exceptions.ResponseError;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.ProductCreateException;
import by.it.academy.ishop.exceptions.UserAuthenticationException;
import by.it.academy.ishop.exceptions.UserRegistrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler class for exceptions.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * This method handles EntityByIdNotFoundException.
     * @param exception - exception, which throw methods, if they can`t find entity with current id.
     * @return ResponseError with text "Entity with ID=%d not found, please check your request".
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityByIdNotFoundException.class)
    public ResponseError handleEntityByIdNotFoundException (EntityByIdNotFoundException exception){
        log.info((exception.toString()));
        return new ResponseError(exception.getMessage());
    }

    /**
     * This method handles UserAuthenticationException.
     * @param exception - exception, which throw authentication method, if login or password incorrect.
     * @return ResponseError - "Login or password incorrect".
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseError handleUserAuthenticationException (UserAuthenticationException exception){
        log.info((exception.toString()));
        return new ResponseError(exception.getMessage());
    }

    /**
     * This method handles UserRegistrationException.
     * @param exception - exception, which throw registration method, if login or email exist in DB.
     * @return ResponseError with text "User with such login or email have already been registered"
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserRegistrationException.class)
    public ResponseError handleUserRegistrationException (UserRegistrationException exception){
        log.info((exception.toString()));
        return new ResponseError(exception.getMessage());
    }

    /**
     * This method handles ProductCreateException.
     * @param exception - exception, which throw create product method, if model exist in DB.
     * @return ResponseError with text "This model has already been created. Just update her!".
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductCreateException.class)
    public ResponseError handleProductCreateException (ProductCreateException exception){
        log.info((exception.toString()));
        return new ResponseError(exception.getMessage());
    }

    /**
     * This method handles defaultException.
     * @param exception - default exception.
     * @return ResponseError.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseError defaultException (RuntimeException exception){
        log.info((exception.toString()));
        return new ResponseError(exception.getMessage());
    }
}
