package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j
public class ProductApiService implements ProductService<Product> {
    private final ProductRepository<Product> repository;
    private final Session session;

    public ProductApiService(ProductRepository<Product> products, Session session) {
        this.repository = products;
        this.session = session;
    }


    @Override
    public void create(Product product) {
        try {
            session.beginTransaction();
            repository.create(product);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            session.beginTransaction();
            repository.delete(id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }


    }

    @Override
    public void update(Product product) {
        try {
            session.beginTransaction();
            repository.update(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
    }

    @Override
    public void buy(Product product) {
        try {
            session.beginTransaction();
            repository.buy(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }


    }

    @Override
    public Product getProduct(Long id) {
        return repository.getProduct(id)
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " is not exists"));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            session.beginTransaction();
            products = repository.getAllProducts();
            session.getTransaction().commit();

        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
        return products;

    }
}
