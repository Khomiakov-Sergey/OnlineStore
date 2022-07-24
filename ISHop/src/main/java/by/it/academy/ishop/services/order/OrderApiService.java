package by.it.academy.ishop.services.order;

import by.it.academy.ishop.dtos.OrderStatusDto;
import by.it.academy.ishop.dtos.requests.OrderRequestDto;
import by.it.academy.ishop.dtos.responds.OrderRespondDto;
import by.it.academy.ishop.entities.order.Order;
import by.it.academy.ishop.entities.order.Status;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service class for orders with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApiService implements OrderService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    private final OrderMapper orderMapper;

    /**
     * This method searches order by the transferred orderId using OrderRepository.
     * If it doesn`t exist -> throw EntityByIdNotFoundException.
     * @param orderId - Order identifier.
     * @return OrderRespondDto - Order representative.
     */
    @Override
    @Transactional
    public OrderRespondDto findOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityByIdNotFoundException(orderId));
        return orderMapper.orderToDto(order);
    }

    /**
     * This method searches orders by the transferred user id using OrderRepository.
     * @param userId - User identifier.
     * @return List<OrderRespondDto> - List of all orders for current user.
     */
    @Override
    @Transactional
    public List<OrderRespondDto> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId);
        return orders.stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method creates new order and transfers it in OrderRepository to save.
     * @param orderRequestDto - Product id, User id, products quantity for order.
     * @return List<OrderRespondDto> - List of all orders for current user.
     */
    @Override
    @Transactional
    public List<OrderRespondDto> createOrder(OrderRequestDto orderRequestDto) {
        Long productId = orderRequestDto.getProduct().getId();
        orderRepository.save(buildOrder(orderRequestDto));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityByIdNotFoundException(productId));
        product.setNumber(product.getNumber() - orderRequestDto.getQuantity());
        return this.getOrdersByUserId(orderRequestDto.getUser().getId());
    }

    /**
     * This method updates order`s status to CANCELED and transfers it in OrderRepository to save.
     * @param orderId - Order identifier.
     * @param userId - User identifier.
     */
    @Override
    @Transactional
    public void cancelOrderByOrderIdAndUserId(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityByIdNotFoundException(orderId));
        order.setOrderStatus(orderStatusRepository.findByStatus(Status.CANCELLED)
                .orElseThrow(NoSuchElementException::new));
    }

    /**
     * This method updates order`s status and transfers it in OrderRepository to save.
     * @param orderId - Order identifier.
     * @param orderStatusDto - Product id, User id, products quantity for order.
     * @return id - Order identifier.
     */
    @Override
    @Transactional
    public Long updateStatusOrder(Long orderId, OrderStatusDto orderStatusDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityByIdNotFoundException(orderId));
        order.setOrderStatus(orderStatusRepository.findByStatus(orderStatusDto.getStatus())
                .orElseThrow(NoSuchElementException::new));
        return order.getId();
    }

    /**
     * This intermediate method helps main method <>createOrder</> build cart from request.
     * @param orderRequestDto - Product id, User id, products quantity for order.
     * @return order - New Order.
     */
    private Order buildOrder(OrderRequestDto orderRequestDto){
        Long productId = orderRequestDto.getProduct().getId();
        Long userId = orderRequestDto.getUser().getId();

        return Order.builder()
                .product(productRepository.findById(productId).
                        orElseThrow(() -> new EntityByIdNotFoundException(productId)))
                .user(userRepository.findById(userId).
                        orElseThrow(() -> new EntityByIdNotFoundException(userId)))
                .amount(productRepository.findById(productId)
                        .orElseThrow(() -> new EntityByIdNotFoundException(productId)).getPrice()
                        .multiply(BigDecimal.valueOf(orderRequestDto.getQuantity())))
                .quantity(orderRequestDto.getQuantity())
                .orderStatus(orderStatusRepository.findByStatus(Status.CREATED)
                        .orElseThrow(NoSuchElementException::new))
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }
}
