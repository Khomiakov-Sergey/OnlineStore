package by.it.academy.repositories.order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository<T> {
    void create(T order);

    void delete(int id);

    void update(T order);

    Optional<T> getOrder(Long id);

    List<T> getAllOrders();

    List<T> getAllOrdersByUserId(Long id);

}
