package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.ConnectionInt;
import by.it.academy.repositories.connection.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductApiRepository implements ProductRepository<Product> {
    private final ConnectionInt connection;

    public ProductApiRepository(ConnectionInt connection) {
        this.connection = new MySQLConnection();
    }


    @Override
    public void create(Product product) throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO PRODUCT_LIST(NAME, PRICE, NUMBER, DESCRIPTION) values(?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, product.getName());
        pstm.setDouble(2, product.getPrice());
        pstm.setInt(3, product.getNumber());
        pstm.setString(4, product.getDescription());
        pstm.executeUpdate();
        conn.close();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "DELETE FROM PRODUCT_LIST WHERE ID= ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
        conn.close();
    }

    @Override
    public void update(int id, String name, double price, int number, String description) {

    }

    @Override
    public Optional<Product> getProduct(String name) {
        return Optional.empty();
    }

    @Override


    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "Select * from PRODUCT_LIST";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("NAME");
            double price = rs.getDouble("PRICE");
            int number = rs.getInt("NUMBER");
            String description = rs.getString("DESCRIPTION");
            Product product = new Product(name, price, number, description);
            list.add(product);
        }
        conn.close();
        return list;
    }
}
