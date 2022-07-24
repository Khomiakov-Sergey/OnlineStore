package by.it.academy.ishop.controllers.cart;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for carts. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * This method creates new order with using service layer.
     * @param cartDto - Product id, User id, products quantity and price for cart.
     * @return List<CartDto> - List of all carts for current user.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<CartDto> createCart(@RequestBody @Valid CartDto cartDto) {
        return cartService.createCart(cartDto);
    }

    /**
     * This method gets user identifier and tries to find list of carts with using service layer.
     * For all roles.
     * @param userId - user identifier.
     * @return List<CartDto> - List of all carts for current user.
     */
    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto> getCartsByUserId(@PathVariable Long userId) {
        return cartService.findCartsByUserId(userId);
    }

    /**
     * This method gets user identifier, cart identifier and tries to delete cart with using service layer.
     * For all role
     * @param userId - User identifier.
     * @param cartId - Cart identifier.
     * @return List<CartDto> - List of all carts for current user.
     */
    @DeleteMapping
    public List<CartDto> deleteCart(@RequestParam(value = "cartId") Long cartId, @RequestParam(value = "userId") Long userId) {
        return cartService.deleteCartByCartIdAndUserId(cartId, userId);
    }
}
