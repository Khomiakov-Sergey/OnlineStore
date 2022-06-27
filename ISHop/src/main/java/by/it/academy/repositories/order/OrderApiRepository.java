package by.it.academy.repositories.order;

import by.it.academy.entities.Order;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * This repository class is responsible for transferred orders information from services to DB using by hibernate.
 */
@Log4j
public class OrderApiRepository implements OrderRepository<Order> {

    private final Session session;

    public OrderApiRepository(Session session) {
        this.session = session;
    }

    /**
     * This method persists order using by hibernate.
     */
    @Override
    public void create(Order order) {
        session.persist(order);
    }

    /**
     * This method deletes order using by hibernate. The method is not implemented.
     */
    @Override
    public void delete(int id) {
    }

    /**
     * This method updates order using by hibernate. The method is not implemented.
     */
    @Override
    public void update(Order order) {

    }

    /**
     * This method gets order using by hibernate and streams.
     * @return Optional<Order>
     */
    @Override
    public Optional<Order> getOrder(Long id) {
        return getAllOrders()
                .stream().filter(order -> order.getId() == (id))
                .findFirst();
    }

    /**
     * This method gets all orders using by criteria and hibernate.
     * @return list of orders
     */
    @Override
    public List<Order> getAllOrders() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Order> orderQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderQuery.from(Order.class);
        orderQuery.select(orderRoot);
        return session.createQuery(orderQuery).getResultList();
    }

    /**
     * This method gets orders for current user using by criteria and hibernate.
     * @return list of orders
     */
    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Order> orderQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderQuery.from(Order.class);
        orderQuery.select(orderRoot).where(criteriaBuilder.equal(orderRoot.get("user"), id));
        return session.createQuery(orderQuery).getResultList();
    }
}
