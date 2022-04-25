package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductApiService implements ProductService<Product> {
    private final ProductRepository<Product> repository;

    public ProductApiService(ProductRepository<Product> products) {
        this.repository = products;
    }


    @Override
    public void create(Product product) {
        repository.create(product);

    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Product product) {
        repository.update(product);

    }

    @Override
    public void buy(Product product) {
        repository.buy(product);
    }

    @Override
    public Product getProduct(int id) {
        return repository.getProduct(id)
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " is not exists"));
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();

    }
}
