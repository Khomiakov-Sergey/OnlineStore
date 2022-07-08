package by.it.academy.ishop.controllers.order;

import by.it.academy.ishop.dtos.requests.OrderDtoRequest;
import by.it.academy.ishop.dtos.responds.OrderDtoRespond;
import by.it.academy.ishop.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("orders")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderDtoRespond> createOrder(@RequestBody @Valid OrderDtoRequest orderDtoRequest) {
        return orderService.createOrder(orderDtoRequest);
    }

    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDtoRespond> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("{Id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDtoRespond getOrder(@PathVariable Long Id) {
        return orderService.getOrder(Id);
    }

}
