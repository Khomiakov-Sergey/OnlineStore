package by.it.academy.ishop.controllers;

import by.it.academy.ishop.dtos.requests.RegistrationProductRequest;
import by.it.academy.ishop.dtos.requests.RegistrationUserRequest;
import by.it.academy.ishop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid RegistrationProductRequest registrationProductRequest){
        return productService.createProduct(registrationProductRequest);
    }
}
