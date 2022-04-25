package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DBConnection;
import by.it.academy.repositories.connection.MySQLConnection;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductApiRepository implements ProductRepository<Product> {
    private final DBConnection connection;

    private final static Logger log = Logger.getLogger(ProductApiRepository.class);

    public ProductApiRepository(DBConnection connection) {
        this.connection = new MySQLConnection();
    }

    @Override
    public void create(Product product) {
        Connection conn;
        try {
            conn = connection.getConnection();
            String sql = "INSERT INTO PRODUCT_LIST(NAME, PRICE, NUMBER, DESCRIPTION) values(?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, product.getName());
            pstm.setBigDecimal(2, product.getPrice());
            pstm.setInt(3, product.getNumber());
            pstm.setString(4, product.getDescription());
            pstm.executeUpdate();
            conn.close();
            log.info("Create product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        Connection conn;
        try {
            conn = connection.getConnection();
            String sql = "DELETE FROM PRODUCT_LIST WHERE ID= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.close();
            log.info("Delete product with next id:" + id);
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        Connection conn;
        try {
            conn = connection.getConnection();
            String sql = "UPDATE PRODUCT_LIST SET NAME=?, PRICE=?, NUMBER=?, DESCRIPTION=? WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, product.getName());
            pstm.setBigDecimal(2, product.getPrice());
            pstm.setInt(3, product.getNumber());
            pstm.setString(4, product.getDescription());
            pstm.setInt(5, product.getId());
            pstm.executeUpdate();
            conn.close();
            log.info("Update product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void buy(Product product) {
        Connection conn;
        try {
            conn = connection.getConnection();
            String sql = "UPDATE PRODUCT_LIST SET NUMBER=? WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, product.getNumber());
            pstm.setInt(2, product.getId());
            pstm.executeUpdate();
            conn.close();
            log.info("Purchase of product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return getAllProducts()
                .stream().filter(product -> product.getId() == (id))
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connection.getConnection();
            String sql = "Select * from PRODUCT_LIST utf8";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                BigDecimal price = (rs.getBigDecimal("PRICE"));
                int number = rs.getInt("NUMBER");
                String description = rs.getString("DESCRIPTION");
                Product product = new Product(name, price, number, description);
                product.setId(id);
                list.add(product);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
        }
        log.info("List of products with next value:" + list);
        return list;
    }
}
