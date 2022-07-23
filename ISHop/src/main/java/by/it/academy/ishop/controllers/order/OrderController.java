package by.it.academy.ishop.controllers.order;

import by.it.academy.ishop.dtos.OrderStatusDto;
import by.it.academy.ishop.dtos.requests.OrderRequestDto;
import by.it.academy.ishop.dtos.responds.OrderRespondDto;
import by.it.academy.ishop.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for orders. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RequestMapping
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * This method creates new order with using service layer.
     * For all roles.
     * @param orderRequestDto - Product id, User id, products quantity for order.
     * @return List<OrderRespondDto> - List of all orders for current user.
     */
    @PostMapping("orders/create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderRespondDto> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    /**
     * This method gets user identifier and tries to find list of orders with using service layer.
     * For all roles.
     * @param userId - user identifier.
     * @return List<OrderRespondDto> - List of all orders for current user.
     */
    @GetMapping("orders/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderRespondDto> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    /**
     * This method gets order identifier and tries to find order with using service layer.
     * For role ADMIN.
     * @param orderId - Order identifier.
     * @return OrderRespondDto - Order representative.
     */
    @GetMapping("admin/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderRespondDto getOrder(@PathVariable Long orderId) {
        return orderService.findOrder(orderId);
    }

    /**
     * This method gets order identifier and tries to find order with using service layer.
     * For role ADMIN.
     * @param orderId        - Order identifier.
     * @param orderStatusDto - Product id, User id, products quantity for order.
     * @return orderId - Order identifier.
     */
    @PutMapping("admin/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateStatusOrder(@PathVariable Long orderId, @RequestBody @Valid OrderStatusDto orderStatusDto) {
        return orderService.updateStatusOrder(orderId, orderStatusDto);
    }

    @PutMapping("orders/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatusOrder(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "userId") Long userId) {
        orderService.cancelOrderByOrderIdAndUserId(orderId, userId);
    }

}
