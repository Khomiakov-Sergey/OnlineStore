package by.it.academy.repositories.product;

import by.it.academy.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {
    void create(T product);

    void delete(int id);

    void update(T product);

    void buy(T product);

    Optional<T> getProduct(int id);

    List<Product> getAllProducts();
}
