package by.it.academy.repositories.product;

import by.it.academy.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {
    void create(T product);

    void delete(Long id);

    void update(T product);

    void buy(T product);

    Optional<T> getProduct(Long id);

    List<Product> getAllProducts();
}
