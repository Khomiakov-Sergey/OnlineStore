package by.it.academy.ishop.exceptions;

/**
 * Exception class for product, that has already been created.
 * @author Siarhei Khamiakou
 * @version 1.0
 */

public class ProductCreateException extends IllegalArgumentException {

    private static final String PRODUCT_ALREADY_EXIST = "This model has already been created. Just update her!";

    public ProductCreateException() {
        super(PRODUCT_ALREADY_EXIST);
    }
}
