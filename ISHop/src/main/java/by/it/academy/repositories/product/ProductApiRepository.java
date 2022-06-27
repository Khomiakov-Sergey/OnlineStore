package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * This repository class is responsible for transferred product information from services to DB using by hibernate.
 */
@Log4j
public class ProductApiRepository implements ProductRepository<Product> {
    private final Session session;

    public ProductApiRepository(Session session) {
        this.session = session;
    }

    /**
     * This method persists product using by hibernate.
     */
    @Override
    public void create(Product product) {
        session.persist(product);
    }

    /**
     * This method deletes product using by hibernate.
     */
    @Override
    public void delete(Long id) {
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    /**
     * This method updates product using by hibernate.
     */
    @Override
    public void update(Product product) {
        Long id = product.getId();
        Product updateProduct = session.get(Product.class, id);
        updateProduct
                .setCategoryType(product.getCategoryType())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setNumber(product.getNumber())
                .setDescription(product.getDescription());
        session.update(updateProduct);
    }

    /**
     * This method buys product using by hibernate.
     */
    @Override
    public void buy(Product product) {
        Long id = product.getId();
        Product updateProduct = session.get(Product.class, id);
        updateProduct
                .setNumber(product.getNumber());
        session.update(updateProduct);

    }

    /**
     * This method gets product using by hibernate and streams.
     * @return Optional<Product>
     */
    @Override
    public Optional<Product> getProduct(Long id) {
        return getAllProducts()
                .stream().filter(product -> product.getId() == (id))
                .findFirst();
    }

    /**
     * This method gets all products using by criteria and hibernate.
     * @return list of products
     */
    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> productQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productQuery.from(Product.class);
        productQuery.select(productRoot);
        return session.createQuery(productQuery).getResultList();
    }
}
