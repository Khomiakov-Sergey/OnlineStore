package by.it.academy.ishop.repositories.order;

import by.it.academy.ishop.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);

}
