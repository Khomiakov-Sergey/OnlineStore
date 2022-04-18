package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductApiService implements ProductService<Product> {
    private final ProductRepository<Product> repository;

    private final static Logger log = Logger.getLogger(ProductApiService.class);

    public ProductApiService(ProductRepository<Product> products) {
        this.repository = products;
    }


    @Override
    public void create(Product product) {
        try {
            repository.create(product);
        } catch (SQLException | ClassNotFoundException e) {
            log.info(e.getMessage());
        }

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        repository.delete(id);
    }

    @Override
    public void update(int id, String name, double price, int number, String description) {

    }

    @Override
    public Product getProduct(String name) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return repository.getAllProducts();
        } catch (SQLException | ClassNotFoundException e) {
            log.info(e.getMessage());
        }
        return Collections.emptyList();
    }
}
