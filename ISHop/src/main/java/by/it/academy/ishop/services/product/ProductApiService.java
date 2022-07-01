package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.requests.RegistrationProductRequest;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.CategoryType;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.repositories.product.CategoryRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Long updateProduct(User user) {
        return null;
    }

    @Override
    public Long createProduct(RegistrationProductRequest registrationProductRequest) {
        final Product product = buildNewProduct(registrationProductRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product buildNewProduct(RegistrationProductRequest request) {
        return Product.builder()
                .model(request.getModel())
                .category(categoryRepository.findByCategoryType(CategoryType.valueOf(request.getCategory())))
                .price(request.getPrice())
                .number(request.getNumber())
                .description(request.getDescription())
                .build();
    }
}
