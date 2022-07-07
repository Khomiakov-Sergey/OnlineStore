package by.it.academy.ishop.services.order;

import by.it.academy.ishop.dtos.requests.OrderDtoRequest;
import by.it.academy.ishop.dtos.responds.OrderDtoRespond;
import by.it.academy.ishop.entities.order.Order;
import by.it.academy.ishop.entities.order.Status;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.mappers.OrderMapper;
import by.it.academy.ishop.repositories.order.OrderRepository;
import by.it.academy.ishop.repositories.order.OrderStatusRepository;
import by.it.academy.ishop.repositories.product.ProductRepository;
import by.it.academy.ishop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApiService implements OrderService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDtoRespond getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        return orderMapper.orderToDto(order);
    }

    @Override
    @Transactional
    public List<OrderDtoRespond> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId);
        return orders.stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderDtoRespond> createOrder(OrderDtoRequest orderDtoRequest) {
        orderRepository.save(buildOrder(orderDtoRequest));
        Product product = productRepository.findById(orderDtoRequest.getProduct().getId())
                .orElseThrow(NoSuchElementException::new);
        product.setNumber(product.getNumber() - orderDtoRequest.getQuantity());
        return this.getOrdersByUserId(orderDtoRequest.getUser().getId());
    }

    private Order buildOrder(OrderDtoRequest orderDtoRequest){
        return Order.builder()
                .product(productRepository.findById(orderDtoRequest.getProduct().getId()).
                        orElseThrow(NoSuchElementException::new))
                .user(userRepository.findById(orderDtoRequest.getUser().getId()).
                        orElseThrow(NoSuchElementException::new))
                .amount(productRepository.findById(orderDtoRequest.getProduct().getId())
                        .orElseThrow(NoSuchElementException::new).getPrice()
                        .multiply(BigDecimal.valueOf(orderDtoRequest.getQuantity())))
                .quantity(orderDtoRequest.getQuantity())
                .orderStatus(orderStatusRepository.findByStatus(Status.CREATED))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
