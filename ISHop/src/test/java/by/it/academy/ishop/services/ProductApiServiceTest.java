package by.it.academy.ishop.services;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.CategoryType;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.ProductCreateException;
import by.it.academy.ishop.mappers.ProductMapper;
import by.it.academy.ishop.repositories.product.CategoryRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.services.product.ProductApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for products service")
public class ProductApiServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductApiService productService;

    private Product product;
    private Category category;

    private ProductDto productDto;
    private CategoryDto categoryDto;

    @BeforeEach
    @Test
    void init() {
        category = new Category();
        category.setCategoryType(CategoryType.IPHONE);

        product = new Product();
        product.setCategory(category);
        product.setModel("12");
        product.setPrice(BigDecimal.valueOf(1199.99));
        product.setNumber(23L);
        product.setDescription("Phone");

        categoryDto = new CategoryDto();
        categoryDto.setCategoryType(CategoryType.IPHONE);

        productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setCategory(categoryDto);
        productDto.setModel("12");
        productDto.setPrice(BigDecimal.valueOf(1199.99));
        productDto.setNumber(23L);
        productDto.setDescription("Phone");

    }

    @Test
    @DisplayName("Product creation test for valid values in product")
    void creatProductWhenProductIsValid() {
        Product validProductFromDB = Product.builder()
                .id(2L)
                .build();

        Mockito.when(categoryRepository.findByCategoryType(CategoryType.IPHONE)).thenReturn(category);
        Mockito.when(productRepository.save(product)).thenReturn(validProductFromDB);

        Assertions.assertEquals(validProductFromDB.getId(), productService.createProduct(productDto));

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("Product creation test when product`s already existed in DB")
    void creatProductWhenProductIsAlreadyExist() {
        Product existedProduct = product;
        Mockito.when(categoryRepository.findByCategoryType(CategoryType.IPHONE)).thenReturn(category);
        Mockito.when(productRepository.save(existedProduct)).thenThrow(new ProductCreateException());

        Assertions.assertThrows(ProductCreateException.class, () -> productService.createProduct(productDto));

        Mockito.verify(productRepository, Mockito.times(1)).save(existedProduct);
    }

    @Test
    @DisplayName("Product search test for valid id in product")
    void findProductWhenProductIdIsValid() {
        Long id = 2L;

        Mockito.when(productMapper.productToDto(product)).thenReturn(productDto);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));

        Assertions.assertEquals(productDto, productService.findProduct(id));

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Product search test for invalid id in product")
    void findProductWhenProductIdIsNotExistInDB() {
        Long id = 34L;

        Mockito.when(productRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));

        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> productService.findProduct(id));

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Product update test for valid product id and valid product fields")
    void updateProductWhenProductIdIsExistInDBAndProductFieldsValuesAreValid() {
        Long id = 2L;

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));

        ProductDto newProductDto = ProductDto.builder()
                .model("13")
                .price(BigDecimal.valueOf(1322.88))
                .number(1L)
                .description("Test")
                .build();

        Assertions.assertEquals(product.getId(), productService.updateProduct(id, newProductDto));

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("Product update test for invalid id")
    void updateProductWhenProductIdIsNotExistInDB() {
        Long id = 34L;

        ProductDto newProductDto = ProductDto.builder()
                .model("13")
                .price(BigDecimal.valueOf(1322.88))
                .number(1L)
                .description("Test")
                .build();

        Mockito.when(productRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));

        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> productService.updateProduct(id, newProductDto));

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("The test of finding all products, when some products have existed in DB")
    void getAllProductsFromDBWhereExistSomeProducts() {

        Pageable pageable = PageRequest.of(0, 10);

        List<Product> products = List.of(product);

        List<ProductDto> productsDto = List.of(productDto);
        Page<Product> productPage = new PageImpl<>(products);

        Mockito.when(productRepository.findAll(pageable)).thenReturn(productPage);
        Mockito.when(productMapper.productToDto(product)).thenReturn(productDto);

        Assertions.assertEquals(productsDto, productService.findProducts(pageable));

        Mockito.verify(productRepository, Mockito.times(1)).findAll(pageable);

    }

    @Test
    @DisplayName("The test of finding all products by chosen category, when some products have existed in DB")
    void getAllProductsFromByChosenCategoryDBWhereExistSomeProducts() {
        List<Product> products = List.of(product);
        List<ProductDto> productsDto = List.of(productDto);

        Mockito.when(productRepository.findProductByCategory_CategoryType(CategoryType.IPHONE)).thenReturn(products);
        Mockito.when(productMapper.productToDto(product)).thenReturn(productDto);

        Assertions.assertEquals(productsDto, productService.findProductsByCategory(categoryDto));

        Mockito.verify(productRepository, Mockito.times(1)).findProductByCategory_CategoryType(CategoryType.IPHONE);

    }


}
