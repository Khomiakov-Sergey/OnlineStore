package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.Category;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductsByCategory(CategoryDto categoryDto);

    ProductDto getProduct(Long id);

    Long updateProduct(Long id, ProductDto productDto);

    Long createProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
