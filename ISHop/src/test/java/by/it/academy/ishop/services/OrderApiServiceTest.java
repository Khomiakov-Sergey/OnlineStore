package by.it.academy.ishop.services;

import by.it.academy.ishop.dtos.OrderStatusDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.requests.OrderRequestDto;
import by.it.academy.ishop.dtos.responds.OrderRespondDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.order.Order;
import by.it.academy.ishop.entities.order.OrderStatus;
import by.it.academy.ishop.entities.order.Status;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.mappers.OrderMapper;
import by.it.academy.ishop.repositories.order.OrderRepository;
import by.it.academy.ishop.repositories.order.OrderStatusRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.services.order.OrderApiService;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for orders service")
public class OrderApiServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderApiService orderService;

    private Order order;
    private OrderRespondDto orderRespondDto;
    private OrderRequestDto orderRequestDto;

    private OrderStatus orderStatus;
    private OrderStatusDto orderStatusDto;

    private User user;
    private UserRespondDto userRespondDto;

    private Product product;
    private ProductDto productDto;

    private List<Order> orders;
    private List<OrderRespondDto> orderRespondDtos;

    @BeforeEach
    @Test
    void init() {
        user = User.builder()
                .id(2L)
                .build();

        userRespondDto = UserRespondDto.builder()
                .id(2L)
                .build();

        product = Product.builder()
                .id(3L)
                .price(BigDecimal.valueOf(1222.22))
                .number(8L)
                .build();

        productDto = ProductDto.builder()
                .id(3L)
                .price(BigDecimal.valueOf(1222.22))
                .number(8L)
                .build();

        orderStatus = OrderStatus.builder()
                .status(Status.CREATED)
                .build();

        orderStatusDto = OrderStatusDto.builder()
                .status(Status.CREATED)
                .build();

        order = Order.builder()
                .user(user)
                .product(product)
                .quantity(4L)
                .amount(BigDecimal.valueOf(4888.88))
                .orderStatus(orderStatus)
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();

        orderRespondDto = OrderRespondDto.builder()
                .user(userRespondDto)
                .product(productDto)
                .quantity(4L)
                .amount(BigDecimal.valueOf(4888.88))
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();

        orderRequestDto = OrderRequestDto.builder()
                .user(userRespondDto)
                .product(productDto)
                .quantity(4L)
                .build();

        orders = List.of(order);
        orderRespondDtos = List.of(orderRespondDto);

    }

    @Test
    @DisplayName("Orders search test for user valid id and orders have existed in DB")
    void getOrdersWhenUserIdIsValidAndUserHasSomeOrders() {
        Long userId = 2L;

        Mockito.when(orderRepository.findOrdersByUserId(userId)).thenReturn(orders);
        Mockito.when(orderMapper.orderToDto(order)).thenReturn(orderRespondDto);

        Assertions.assertEquals(orderRespondDtos, orderService.getOrdersByUserId(userId));

        Mockito.verify(orderRepository, Mockito.times(1)).findOrdersByUserId(userId);
        Mockito.verify(orderMapper, Mockito.times(1)).orderToDto(order);
    }

    @Test
    @DisplayName("Orders search test for user invalid id ")
    void getOrdersWhenUserIdIsNotValid() {
        Long userId = 2L;

        Mockito.when(orderRepository.findOrdersByUserId(userId)).thenThrow(new EntityByIdNotFoundException(userId));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> orderService.getOrdersByUserId(userId));

        Mockito.verify(orderRepository, Mockito.times(1)).findOrdersByUserId(userId);
    }

    @Test
    @DisplayName("Order creation test for valid orders values")
    void creatOrderWhenOrderValueIsValid() {
        Mockito.when(orderStatusRepository.findByStatus(Status.CREATED)).thenReturn(orderStatus);
        Mockito.when(productRepository.findById(orderRequestDto.getProduct().getId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(userRepository.findById(orderRequestDto.getUser().getId())).thenReturn(Optional.ofNullable(user));

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Mockito.when(orderRepository.findOrdersByUserId(orderRequestDto.getUser().getId())).thenReturn(orders);
        Mockito.when(orderMapper.orderToDto(order)).thenReturn(orderRespondDto);

        Assertions.assertEquals(orderRespondDtos, orderService.createOrder(orderRequestDto));

        Mockito.verify(productRepository, Mockito.times(3)).findById(orderRequestDto.getProduct().getId());
        Mockito.verify(userRepository, Mockito.times(1)).findById(orderRequestDto.getUser().getId());

        Mockito.verify(orderRepository, Mockito.times(1)).save(order);

        Mockito.verify(orderRepository, Mockito.times(1)).findOrdersByUserId(orderRequestDto.getUser().getId());
        Mockito.verify(orderMapper, Mockito.times(1)).orderToDto(order);
    }

}
