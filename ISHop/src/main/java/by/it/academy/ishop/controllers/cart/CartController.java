package by.it.academy.ishop.controllers.cart;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CartDto> createCart(@RequestBody @Valid CartDto cartDto) {
        return cartService.addCartByUserIdAndProductId(cartDto);
    }

    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto> getCartsByUserId(@PathVariable Long userId) {
        return cartService.getCartsByUserId(userId);
    }

    @DeleteMapping
    public List<CartDto> deleteCart(@RequestParam(value = "cartId") Long cartId, @RequestParam(value = "userId") Long userId) {
        return cartService.deleteCartByCartIdAndUserId(cartId, userId);
    }
}
