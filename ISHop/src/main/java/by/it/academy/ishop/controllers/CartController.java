package by.it.academy.ishop.controllers;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CartDto> createUser(@RequestBody @Valid CartDto cartDto) {
        return cartService.addCartByUserIdAndProductId(cartDto);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto>  getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }
}
