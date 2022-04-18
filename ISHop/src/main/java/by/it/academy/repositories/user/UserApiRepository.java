package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.ConnectionInt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserApiRepository implements UserRepository<User> {
    private final ConnectionInt connection;

    public UserApiRepository(ConnectionInt connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO USER_LIST(FIRST_NAME, SECOND_NAME, AGE, LOGIN, PASSWORD) values(?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getFirstName());
        pstm.setString(2, user.getSecondName());
        pstm.setInt(3, user.getAge());
        pstm.setString(4, user.getLogin());
        pstm.setString(5, user.getPassword());
        pstm.executeUpdate();
        conn.close();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials) {

    }

    @Override
    public Optional<User> getUser(String login) throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "Select * from USER_LIST u where u.LOGIN = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, login);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String firstName = rs.getString("FIRST_NAME");
            String secondName = rs.getString("SECOND_NAME");
            int age = rs.getInt("AGE");
            String password = rs.getString("PASSWORD");
            User user = new User(firstName, secondName, age, login, password);
            return Optional.of(user);
        }
        return Optional.empty();


    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        Connection conn = connection.getConnection();
        String sql = "Select * from USER_LIST";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            String firstName = rs.getString("FIRST_NAME");
            String secondName = rs.getString("SECOND_NAME");
            int age = rs.getInt("AGE");
            String login = rs.getString("LOGIN");
            String password = rs.getString("PASSWORD");
            User user = new User(firstName, secondName, age, login, password);
            list.add(user);
        }
        conn.close();
        return list;
    }
}
