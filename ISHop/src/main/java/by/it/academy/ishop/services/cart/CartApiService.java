package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.mappers.CartMapper;
import by.it.academy.ishop.repositories.cart.CartRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for carts(buckets) with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartApiService implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final CartMapper cartMapper;

    /**
     * This method creates new cart and transfers it in CartRepository to save.
     *
     * @param cartDto - Product id, User id, products quantity and price for cart.
     * @return List<CartDto> - List of all carts for current user.
     */
    @Override
    @Transactional
    public List<CartDto> createCart(CartDto cartDto) {
        cartRepository.save(buildCart(cartDto));
        return this.findCartsByUserId(cartDto.getUser().getId());
    }

    /**
     * This method searches carts by the transferred user id using CartRepository.
     * @param userId - user identifier.
     * @return List<CartDto> - List of all carts for current user.
     */
    @Override
    @Transactional
    public List<CartDto> findCartsByUserId(Long userId) {
        List<Cart> carts = cartRepository.findCartsByUserId(userId);
        return carts.stream()
                .map(cartMapper::cartToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method searches carts by the transferred userId and cartId. If it exists, deletes it by using CartRepository.
     * @param userId - User identifier.
     * @param cartId - Cart identifier.
     * @return List<CartDto> - List of all carts for current user.
     */
    @Override
    @Transactional
    public List<CartDto> deleteCartByCartIdAndUserId(Long cartId, Long userId) {
        cartRepository.deleteCartByIdAndUserId(cartId, userId);
        return this.findCartsByUserId(userId);
    }

    /**
     * This method searches carts by the transferred cartId using CartRepository.
     * If it doesn`t exist -> throw EntityByIdNotFoundException.
     * @param cartId - Cart identifier.
     * @return CartDto - Cart representative.
     */
    @Override
    @Transactional
    public CartDto findCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new EntityByIdNotFoundException(cartId));
        return cartMapper.cartToDto(cart);
    }

    /**
     * This intermediate method helps main method <>createCart</> build cart from request.
     * @param cartDto - Product id, User id, products quantity and price for cart.
     * @return cart - New Cart.
     */
    private Cart buildCart(CartDto cartDto) {
        Long productId = cartDto.getProduct().getId();
        Long userId = cartDto.getUser().getId();

        return Cart.builder()
                .product(productRepository.findById(productId).
                        orElseThrow(() -> new EntityByIdNotFoundException(productId)))
                .cost(productRepository.findById(productId).
                        orElseThrow(() -> new EntityByIdNotFoundException(productId)).getPrice())
                .user(userRepository.findById(userId).
                        orElseThrow(() -> new EntityByIdNotFoundException(userId)))
                .quantity(cartDto.getQuantity())
                .build();
    }
}
