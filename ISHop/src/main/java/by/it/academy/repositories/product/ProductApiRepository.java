package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DBConnection;
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
        this.connection = connection;
    }

    @Override
    public void create(Product product) {
        try (Connection conn = connection.getConnection()) {
            String sql = "INSERT INTO PRODUCT_LIST(CATEGORY_ID,NAME, PRICE, NUMBER, DESCRIPTION) values(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, product.getCategoryId());
            pstm.setString(2, product.getName());
            pstm.setBigDecimal(3, product.getPrice());
            pstm.setInt(4, product.getNumber());
            pstm.setString(5, product.getDescription());
            pstm.executeUpdate();
            log.info("Create product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t create product" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM PRODUCT_LIST WHERE ID= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            log.info("Delete product with next id:" + id);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t delete product" + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        try (Connection conn = connection.getConnection()) {
            String sql = "UPDATE PRODUCT_LIST SET CATEGORY_ID=?, NAME=?, PRICE=?, NUMBER=?, DESCRIPTION=? WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, product.getCategoryId());
            pstm.setString(2, product.getName());
            pstm.setBigDecimal(3, product.getPrice());
            pstm.setInt(4, product.getNumber());
            pstm.setString(5, product.getDescription());
            pstm.setInt(6, product.getId());
            pstm.executeUpdate();
            log.info("Update product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t update product" + e.getMessage());
        }
    }

    @Override
    public void buy(Product product) {
        try (Connection conn = connection.getConnection()) {
            String sql = "UPDATE PRODUCT_LIST SET NUMBER=? WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, product.getNumber());
            pstm.setInt(2, product.getId());
            pstm.executeUpdate();
            log.info("Purchase of product with next value:" + product);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t buy product" + e.getMessage());
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
        try (Connection conn = connection.getConnection()) {
            String sql = "Select * from PRODUCT_LIST";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int category_id = rs.getInt("CATEGORY_ID");
                String name = rs.getString("NAME");
                BigDecimal price = (rs.getBigDecimal("PRICE"));
                int number = rs.getInt("NUMBER");
                String description = rs.getString("DESCRIPTION");
                Product product = new Product(category_id, name, price, number, description);
                product.setId(id);
                list.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t get list of products" + e.getMessage());
        }
        log.info("Get list of products");
        return list;
    }
}
