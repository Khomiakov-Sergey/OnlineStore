package by.it.academy.repositories.order;

import by.it.academy.entities.Order;
import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DataSource;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@Log4j
public class OrderApiRepository implements OrderRepository<Order>{

    private  final Session session;

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
    public Optional<Order> getOrder(int id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllOrders() {
        return null;
    }
}
