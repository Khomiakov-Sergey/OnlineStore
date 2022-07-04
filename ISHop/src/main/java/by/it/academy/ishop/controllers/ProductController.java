package by.it.academy.ishop.controllers;

import by.it.academy.ishop.dtos.requests.ProductDto;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

}
