package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Log4j
public class ProductApiRepository implements ProductRepository<Product> {
    private final Session session;

    public ProductApiRepository(Session session) {
        this.session = session;
    }

    @Override
    public void create(Product product) {
        session.save(product);
    }

    @Override
    public void delete(int id) {
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    @Override
    public void update(Product product) {
        int id = product.getId();
        Product updateProduct = session.get(Product.class, id);
        updateProduct.setCategoryType(product.getCategoryType());
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setNumber(product.getNumber());
        updateProduct.setDescription(product.getDescription());
        session.update(updateProduct);

    }


    @Override
    public void buy(Product product) {
        int id = product.getId();
        Product updateProduct = session.get(Product.class, id);
        updateProduct.setNumber(product.getNumber());
        session.update(updateProduct);

    }

    @Override
    public Optional<Product> getProduct(int id) {
        return getAllProducts()
                .stream().filter(product -> product.getId() == (id))
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.select(productRoot);
        return session.createQuery(query).getResultList();
    }
}
