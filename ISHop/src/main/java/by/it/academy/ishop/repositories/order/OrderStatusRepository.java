package by.it.academy.ishop.repositories.order;

import by.it.academy.ishop.entities.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
