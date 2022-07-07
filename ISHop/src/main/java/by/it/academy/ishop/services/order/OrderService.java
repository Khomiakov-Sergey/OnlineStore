package by.it.academy.ishop.services.order;

import by.it.academy.ishop.dtos.requests.OrderDtoRequest;
import by.it.academy.ishop.dtos.responds.OrderDtoRespond;

import java.util.List;

public interface OrderService {

    OrderDtoRespond getOrder(Long orderId);

    List<OrderDtoRespond> getOrdersByUserId(Long userId);

    List<OrderDtoRespond> createOrder(OrderDtoRequest orderDtoRequest);
}
