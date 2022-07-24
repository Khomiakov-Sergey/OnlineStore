package by.it.academy.ishop.services.cart;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.mappers.CartMapper;
import by.it.academy.ishop.repositories.cart.CartRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.services.cart.CartApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for carts service")
public class CartApiServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartApiService cartService;

    private Cart cart;
    private CartDto cartDto;
    private User user;
    private Product product;

    private List<Cart> carts;
    private List<CartDto> cartsDto;

    @BeforeEach
    @Test
    void init() {
        user = User.builder()
                .id(2L)
                .build();

        product = Product.builder()
                .id(3L)
                .price(BigDecimal.valueOf(1222.22))
                .build();

        cart = Cart.builder()
                .user(user)
                .product(product)
                .quantity(3L)
                .cost(BigDecimal.valueOf(1222.22))
                .build();

        cartDto = CartDto.builder()
                .user(UserRespondDto.builder()
                        .id(2L)
                        .build())
                .product(ProductDto.builder()
                        .id(3L)
                        .build())
                .quantity(3L)
                .cost(BigDecimal.valueOf(1222.22))
                .build();

        carts = List.of(cart);
        cartsDto = List.of(cartDto);
    }

    @Test
    @DisplayName("Cart creation test for valid carts values")
    void checkResponseFor_CreatCart_MethodWhenCartValueIsValid() {

        Mockito.when(productRepository.findById(cartDto.getProduct().getId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(userRepository.findById(cartDto.getUser().getId())).thenReturn(Optional.ofNullable(user));

        Mockito.when(cartRepository.save(cart)).thenReturn(cart);

        Mockito.when(cartRepository.findCartsByUserId(cartDto.getUser().getId())).thenReturn(carts);
        Mockito.when(cartMapper.cartToDto(cart)).thenReturn(cartDto);

        Assertions.assertEquals(cartsDto, cartService.createCart(cartDto));

        Mockito.verify(productRepository, Mockito.times(2)).findById(cartDto.getProduct().getId());
        Mockito.verify(userRepository, Mockito.times(1)).findById(cartDto.getUser().getId());

        Mockito.verify(cartRepository, Mockito.times(1)).save(cart);

        Mockito.verify(cartRepository, Mockito.times(1)).findCartsByUserId(cartDto.getUser().getId());
        Mockito.verify(cartMapper, Mockito.times(1)).cartToDto(cart);
    }

    @Test
    @DisplayName("Cart creation test for invalid product value")
    void checkResponseFor_CreatCart_MethodWhenProductValueIsNotValid() {

        Mockito.when(productRepository.findById(cartDto.getProduct().getId()))
                .thenThrow(new EntityByIdNotFoundException(cartDto.getProduct().getId()));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> cartService.createCart(cartDto));

        Mockito.verify(productRepository, Mockito.times(1)).findById(cartDto.getProduct().getId());

    }

    @Test
    @DisplayName("Cart creation test for invalid user value")
    void checkResponseFor_CreatCart_MethodWhenUserValueIsNotValid() {

        Mockito.when(productRepository.findById(cartDto.getProduct().getId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(userRepository.findById(cartDto.getUser().getId()))
                .thenThrow(new EntityByIdNotFoundException(cartDto.getUser().getId()));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> cartService.createCart(cartDto));

        Mockito.verify(userRepository, Mockito.times(1)).findById(cartDto.getUser().getId());

    }

    @Test
    @DisplayName("Carts search test for user valid id and carts have existed in DB")
    void checkResponseFor_FindCartsByUserId_MethodWhenUserIdIsValidAndUserHasSomeCarts() {
        Long userId = 2L;

        Mockito.when(cartRepository.findCartsByUserId(userId)).thenReturn(carts);
        Mockito.when(cartMapper.cartToDto(cart)).thenReturn(cartDto);

        Assertions.assertEquals(cartsDto, cartService.findCartsByUserId(userId));

        Mockito.verify(cartRepository, Mockito.times(1)).findCartsByUserId(userId);
        Mockito.verify(cartMapper, Mockito.times(1)).cartToDto(cart);
    }

    @Test
    @DisplayName("Carts search test for user invalid id and carts have existed in DB")
    void checkResponseFor_FindCartsByUserId_MethodWhenUserIdIsNotValid() {
        Long userId = 2L;

        Mockito.when(cartRepository.findCartsByUserId(userId)).thenThrow(new EntityByIdNotFoundException(userId));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> cartService.findCartsByUserId(userId));

        Mockito.verify(cartRepository, Mockito.times(1)).findCartsByUserId(userId);

    }

    @Test
    @DisplayName("Cart search test for cart valid id ")
    void checkResponseFor_FindCartById_MethodWhenCartIdIsValid() {
        Long cartId = 1L;
        cart.setId(1L);

        Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.ofNullable(cart));
        Mockito.when(cartMapper.cartToDto(cart)).thenReturn(cartDto);

        Assertions.assertEquals(cartDto, cartService.findCart(cartId));

        Mockito.verify(cartRepository, Mockito.times(1)).findById(cartId);
        Mockito.verify(cartMapper, Mockito.times(1)).cartToDto(cart);
    }

    @Test
    @DisplayName("Cart search test for cart invalid id ")
    void checkResponseFor_FindCartById_MethodWhenCartIdIsNotValid() {
        Long cartId = 1L;

        Mockito.when(cartRepository.findById(cartId)).thenThrow(new EntityByIdNotFoundException(cartId));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> cartService.findCart(cartId));

        Mockito.verify(cartRepository, Mockito.times(1)).findById(cartId);

    }

}
