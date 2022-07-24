package by.it.academy.ishop.repositories.order;

import by.it.academy.ishop.entities.order.OrderStatus;
import by.it.academy.ishop.entities.order.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    Optional<OrderStatus> findByStatus(Status status);



}
