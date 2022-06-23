package by.it.academy.repositories.order;

import by.it.academy.entities.Order;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Log4j
public class OrderApiRepository implements OrderRepository<Order> {

    private final Session session;

    public OrderApiRepository(Session session) {
        this.session = session;
    }


    @Override
    public void create(Order order) {
        session.persist(order);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return getAllOrders()
                .stream().filter(order -> order.getId() == (id))
                .findFirst();
    }

    @Override
    public List<Order> getAllOrders() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Order> orderQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderQuery.from(Order.class);
        orderQuery.select(orderRoot);
        return session.createQuery(orderQuery).getResultList();
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Order> orderQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderQuery.from(Order.class);
        orderQuery.select(orderRoot).where(criteriaBuilder.equal(orderRoot.get("user"), id));
        return session.createQuery(orderQuery).getResultList();
    }
}
