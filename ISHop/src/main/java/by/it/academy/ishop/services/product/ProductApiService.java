package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.requests.CategoryDto;
import by.it.academy.ishop.dtos.requests.ProductDto;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.mappers.ProductMapper;
import by.it.academy.ishop.repositories.product.CategoryRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Category category) {
        return null/*productRepository.findProductsByCategory(category)*/;
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return productMapper.productToDto(product);
    }

    @Override
    @Transactional
    public Long updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        product.setModel(productDto.getModel());
        product.setCategory(categoryRepository.findByCategoryType(productDto.getCategory().getCategoryType()));
        product.setPrice(productDto.getPrice());
        product.setNumber(productDto.getNumber());
        product.setDescription(productDto.getDescription());
        return product.getId();
    }

    @Override
    public Long createProduct(ProductDto productDto) {
        final Product product = buildNewProduct(productDto);
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product buildNewProduct(ProductDto productDto) {
        return Product.builder()
                .model(productDto.getModel())
                .category(categoryRepository.findByCategoryType(productDto.getCategory().getCategoryType()))
                .price(productDto.getPrice())
                .number(productDto.getNumber())
                .description(productDto.getDescription())
                .build();
    }
}
