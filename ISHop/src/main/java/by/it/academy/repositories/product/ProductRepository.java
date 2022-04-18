package by.it.academy.repositories.product;

import by.it.academy.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {
    void create(T product) throws SQLException, ClassNotFoundException;

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String name, double price, int number, String description);

    Optional<T> getProduct(String name);

    List<Product> getAllProducts() throws SQLException, ClassNotFoundException;
}
