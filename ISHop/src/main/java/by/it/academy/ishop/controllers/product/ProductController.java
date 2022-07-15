package by.it.academy.ishop.controllers.product;

import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for products. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RequestMapping
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * This method creates new product with using service layer.
     * For role "ADMIN"
     * @param productDto - Product information from request.
     * @return id - Product identifier.
     */
    @PostMapping("admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    /**
     * This method gets all products with using service layer.
     * For all roles.
     * @return List<ProductDto> - List of all products.
     */
    @GetMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProducts() {
        return productService.findProducts();
    }

    /**
     * This method gets all products by chosen category with using service layer.
     * For all roles.
     * @param categoryDto -  Category information from request.
     * @return List<ProductDto> - List of all products.
     */
    @GetMapping("products/category")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto>  getProductsByCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return productService.findProductsByCategory(categoryDto);
    }

    /**
     * This method gets product identifier and tries to find product with using service layer.
     * For all roles.
     * @param id - Product identifier.
     * @return ProductDto - Product representation.
     */
    @GetMapping("products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    /**
     * This method gets user identifier and tries to delete user with using service layer.
     * For role "ADMIN"
     * @param id - Product identifier.
     */
    @DeleteMapping("admin/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    /**
     * This method gets product identifier, productDto and tries to update product with using service layer.
     * For role "ADMIN"
     * @param id - Product identifier.
     * @param productDto - Product information from request.
     * @return id - Product identifier.
     */
    @PutMapping("admin/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

}
