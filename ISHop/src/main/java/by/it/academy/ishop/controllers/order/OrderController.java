package by.it.academy.ishop.controllers.order;

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
@RequestMapping("orders")
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
    @PostMapping()
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
    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderRespondDto> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    /**
     * This method gets order identifier and tries to find order with using service layer.
     * @param id - Order identifier.
     * @return OrderRespondDto - Order representative.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderRespondDto getOrder(@PathVariable Long id) {
        return orderService.findOrder(id);
    }

}
