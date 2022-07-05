package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.mappers.CartMapper;
import by.it.academy.ishop.repositories.cart.CartRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartApiService implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final CartMapper cartMapper;


    @Override
    @Transactional
    public List<CartDto> addCartByUserIdAndProductId(CartDto cartDto) {
        cartRepository.save(createCart(cartDto));
        return this.getCartByUserId(cartDto.getUser().getId());
    }

    @Override
    @Transactional
    public List<CartDto> getCartByUserId(Long userId) {
        List<Cart> carts = cartRepository.findCartByUserId(userId);
        return carts.stream()
                .map(cartMapper::cartToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CartDto> removeCartByUserId(Long cartId, Long userId) {
        cartRepository.deleteCartByIdAndUserId(cartId, userId);
        return this.getCartByUserId(userId);
    }

    private Cart createCart(CartDto cartDto) {
        return Cart.builder()
                .product(productRepository.findById(cartDto.getProduct().getId()).orElseThrow(NoSuchElementException::new))
                .cost(productRepository.findById(cartDto.getProduct().getId()).get().getPrice())
                .user(userRepository.findById(cartDto.getUser().getId()).orElseThrow(NoSuchElementException::new))
                .quantity(cartDto.getQuantity())
                .build();
    }
}
