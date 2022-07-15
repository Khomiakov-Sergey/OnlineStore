package by.it.academy.ishop.services.product;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.ProductCreateException;
import by.it.academy.ishop.mappers.ProductMapper;
import by.it.academy.ishop.repositories.product.CategoryRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class for products with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    /**
     * This method searches all products by using ProductRepository.
     * @return List<ProductDto> - List of all products.
     */
    @Override
    @Transactional
    public List<ProductDto> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method searches all products by chosen category using ProductRepository.
     * @param categoryDto -  Category information from request.
     * @return List<ProductDto> - List of all products.
     */
    @Override
    @Transactional
    public List<ProductDto> findProductsByCategory(CategoryDto categoryDto) {
        List<Product> products = productRepository.findProductByCategory_CategoryType(categoryDto.getCategoryType());
        return products.stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method searches product by the transferred id using ProductRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException.
     * @param id - Product identifier.
     * @return ProductDto - Product representation.
     */
    @Override
    @Transactional
    public ProductDto findProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityByIdNotFoundException(id));
        return productMapper.productToDto(product);
    }

    /**
     * This method searches product by the transferred id and updates information by using ProductRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException.
     * @param id - Product identifier.
     * @param productDto - Product information from request.
     * @return id - Product identifier.
     */
    @Override
    @Transactional
    public Long updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityByIdNotFoundException(id));
        product.setModel(productDto.getModel());
        product.setCategory(categoryRepository.findByCategoryType(productDto.getCategory().getCategoryType()));
        product.setPrice(productDto.getPrice());
        product.setNumber(productDto.getNumber());
        product.setDescription(productDto.getDescription());
        return product.getId();
    }

    /**
     * This method creates new product and transfers it in ProductRepository to save.
     * @param productDto - Product information from request.
     * @return id - Product identifier.
     */
    @Override
    @Transactional
    public Long createProduct(ProductDto productDto) {
        final Product product = buildNewProduct(productDto);
        return productRepository.save(product).getId();
    }

    /**
     * This method searches product by the transferred id. If it exists, deletes it by using ProductRepository.
     * @param id - Product identifier.
     */
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * This intermediate method helps main method <>createProduct</> build product from request.
     * If admin tries to build product with not unique model -> throw ProductCreateException.
     * @param productDto - Product information from request.
     * @return user - New Product.
     */
    private Product buildNewProduct(ProductDto productDto) {
        Product product = productRepository.findProductByModel(productDto.getModel());
        if (Objects.nonNull(product)){
            throw new ProductCreateException();
        }
        return Product.builder()
                .model(productDto.getModel())
                .category(categoryRepository.findByCategoryType(productDto.getCategory().getCategoryType()))
                .price(productDto.getPrice())
                .number(productDto.getNumber())
                .description(productDto.getDescription())
                .build();
    }
}
