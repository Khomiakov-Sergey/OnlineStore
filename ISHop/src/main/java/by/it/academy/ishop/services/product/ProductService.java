package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    List<ProductDto> getProductsByCategory(CategoryDto categoryDto);

    ProductDto getProduct(Long id);

    Long updateProduct(Long id, ProductDto productDto);

    Long createProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
