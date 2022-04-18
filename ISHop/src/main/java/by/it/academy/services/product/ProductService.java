package by.it.academy.services.product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService<T> {
    void create(T product);

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String name, double price, int number, String description);

    T getProduct(String name);

    List<T> getAllProducts() throws SQLException, ClassNotFoundException;


}
