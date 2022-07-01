package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.requests.RegistrationProductRequest;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(Category category);

    Product getProduct(Long id);

    Long updateProduct(User user);

    Long createProduct(RegistrationProductRequest registrationProductRequest);

    void deleteProduct(Long id);
}
