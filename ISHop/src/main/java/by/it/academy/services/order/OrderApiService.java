package by.it.academy.services.order;

import by.it.academy.entities.Order;
import by.it.academy.repositories.order.OrderRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This service class is responsible for transferred orders information from controllers to repository layer.
 * It contains business logic and opens transaction for working in session.
 */
@Log4j
public class OrderApiService implements OrderService<Order> {

    private final OrderRepository<Order> repository;
    private final Session session;

    public OrderApiService(OrderRepository<Order> orders, Session session) {
        this.repository = orders;
        this.session = session;
    }

    /**
     * This method opens transaction, gets the order from the parameter and sends it to the repository layer for
     * creating a new order. After that transaction will be commited.
     */
    @Override
    public void create(Order order) {
        try {
            session.beginTransaction();
            repository.create(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
    }

    /**
     * This method opens transaction, gets the order id from the parameter and sends it to the repository layer for
     * deleting the order. After that transaction will be commited. The method is not implemented.
     */
    @Override
    @Transactional
    public void delete(Long id) {
    }

    /**
     * This method opens transaction, gets the user from the parameter and sends it to the repository layer for
     * updating the user. After that transaction will be commited. The method is not implemented.
     */
    @Override
    @Transactional
    public void update(Order order) {
    }

    /**
     * This method gets the order id from the parameter and sends it to the repository layer for searching the order.
     *
     * @return Order
     */
    @Override
    public Order getOrder(Long id) {
        return repository.getOrder(id)
                .orElseThrow(() -> new NoSuchElementException("Order with id " + id + " is not exists"));
    }

    /**
     * This method opens transaction, gets all orders from repository layer. After that transaction will be commited.
     *
     * @return list of orders
     */
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            session.beginTransaction();
            orders = repository.getAllOrders();
            session.getTransaction().commit();

        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
        return orders;
    }

    /**
     * This method opens transaction, gets all orders from repository layer for current user. After that transaction
     * will be commited.
     *
     * @return list of orders
     */
    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        List<Order> ordersForCurrentUser = new ArrayList<>();
        try {
            session.beginTransaction();
            ordersForCurrentUser = repository.getAllOrdersByUserId(id);
            session.getTransaction().commit();

        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
        return ordersForCurrentUser;
    }
}
