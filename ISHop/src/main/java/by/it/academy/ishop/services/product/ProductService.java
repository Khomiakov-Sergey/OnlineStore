package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.requests.ProductDto;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductsByCategory(Category category);

    ProductDto getProduct(Long id);

    Long updateProduct(Long id, ProductDto productDto);

    Long createProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
