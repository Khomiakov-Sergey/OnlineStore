package by.it.academy.ishop.repositories.order;

import by.it.academy.ishop.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
