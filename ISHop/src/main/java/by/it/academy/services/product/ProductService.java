package by.it.academy.services.product;

import java.util.List;

public interface ProductService<T> {
    void create(T product);

    void delete(Long id);

    void update(T product);

    void buy(T product);

    T getProduct(Long id);

    List<T> getAllProducts();


}
