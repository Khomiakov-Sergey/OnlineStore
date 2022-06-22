package by.it.academy.repositories.order;

import by.it.academy.entities.Product;

import java.util.List;
import java.util.Optional;

public interface OrderRepository <T>{
    void create(T order);

    void delete(int id);

    void update(T order);

    Optional<T> getOrder(int id);

    List<Product> getAllOrders();
}
