package by.it.academy.services.order;

import by.it.academy.entities.Order;
import by.it.academy.repositories.order.OrderRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j
public class OrderApiService implements OrderService<Order> {

    private final OrderRepository<Order> repository;
    private final Session session;

    public OrderApiService(OrderRepository<Order> orders, Session session) {
        this.repository = orders;
        this.session = session;
    }

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

    @Override
    @Transactional
    public void delete(int id) {

    }

    @Override
    @Transactional
    public void update(Order order) {

    }

    @Override
    public Order getOrder(Long id) {
        return repository.getOrder(id)
                .orElseThrow(() -> new NoSuchElementException("Order with id " + id + " is not exists"));
    }

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
