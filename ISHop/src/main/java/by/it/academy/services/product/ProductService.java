package by.it.academy.services.product;

import java.util.List;

public interface ProductService<T> {
    void create(T product);

    void delete(int id);

    void update(T product);

    void buy(T product);

    T getProduct(int id);

    List<T> getAllProducts();


}
