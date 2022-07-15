package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts();

    List<ProductDto> findProductsByCategory(CategoryDto categoryDto);

    ProductDto findProduct(Long id);

    Long updateProduct(Long id, ProductDto productDto);

    Long createProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
