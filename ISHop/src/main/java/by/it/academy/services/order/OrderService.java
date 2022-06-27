package by.it.academy.services.order;

import java.util.List;

public interface OrderService<T> {
    void create(T order);

    void delete(Long id);

    void update(T order);

    T getOrder(Long id);

    List<T> getAllOrders();

    List<T> getAllOrdersByUserId(Long id);

}
