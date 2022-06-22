package by.it.academy.services.order;

import by.it.academy.entities.Order;
import by.it.academy.repositories.order.OrderRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

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
        } catch (HibernateException ex) {
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
    @Transactional
    public Order getOrder(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return null;
    }
}
