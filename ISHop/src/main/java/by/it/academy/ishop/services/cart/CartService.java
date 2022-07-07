package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;

import java.util.List;

public interface CartService {

    List<CartDto> addCartByUserIdAndProductId(CartDto cartDto);

    List<CartDto> getCartsByUserId(Long userId);

    List<CartDto> deleteCartByCartIdAndUserId(Long cartId, Long userId);

    CartDto getCart(Long cartId);
}
