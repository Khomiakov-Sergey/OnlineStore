package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> addCartByUserIdAndProductId(CartDto cartDto);

    List<CartDto> getCartByUserId(Long userId);

    List<CartDto> removeCartByUserId(Long cartId, Long userId);
}
