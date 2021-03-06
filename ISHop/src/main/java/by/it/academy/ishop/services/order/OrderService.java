package by.it.academy.ishop.services.order;

import by.it.academy.ishop.dtos.OrderStatusDto;
import by.it.academy.ishop.dtos.requests.OrderRequestDto;
import by.it.academy.ishop.dtos.responds.OrderRespondDto;

import java.util.List;

public interface OrderService {

    OrderRespondDto findOrder(Long orderId);

    List<OrderRespondDto> getOrdersByUserId(Long userId);

    List<OrderRespondDto> createOrder(OrderRequestDto orderRequestDto);

    void cancelOrderByOrderIdAndUserId(Long orderId, Long userId);

    Long updateStatusOrder(Long orderId, OrderStatusDto orderStatusDto);
}
