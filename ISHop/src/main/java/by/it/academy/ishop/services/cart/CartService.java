package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;

import java.util.List;

public interface CartService {

    List<CartDto> createCart(CartDto cartDto);

    List<CartDto> findCartsByUserId(Long userId);

    List<CartDto> deleteCartByCartIdAndUserId(Long cartId, Long userId);

    CartDto findCart(Long cartId);
}
