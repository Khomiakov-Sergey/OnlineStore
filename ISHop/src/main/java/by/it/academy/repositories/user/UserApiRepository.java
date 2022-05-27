package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.repositories.connection.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserApiRepository implements UserRepository<User> {
    private final DBConnection connection;

    private final static Logger log = Logger.getLogger(UserApiRepository.class);

    public UserApiRepository(DBConnection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (Connection conn = connection.getConnection()) {
            String sql = "INSERT INTO USER_LIST(FIRST_NAME, SECOND_NAME, AGE, LOGIN, PASSWORD) values(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getFirstName());
            pstm.setString(2, user.getSecondName());
            pstm.setInt(3, user.getAge());
            pstm.setString(4, user.getLogin());
            pstm.setString(5, user.getPassword());
            pstm.executeUpdate();
            conn.close();
            log.info("Create user with next value:" + user);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t create new user" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials) {
    }

    @Override
    public Optional<User> getUser(String login, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = connection.getConnection()) {
            String sql = "Select * from USER_LIST";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String secondName = rs.getString("SECOND_NAME");
                String userType = rs.getString("USER_TYPE");
                int age = rs.getInt("AGE");
                String login = rs.getString("LOGIN");
                String password = rs.getString("PASSWORD");
                User user = new User(firstName, secondName, age, login, password);
                user.setUserType(UserType.valueOf(userType));
                user.setId(id);
                list.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Can`t get list of users" + e.getMessage());
        }
        log.info("Get list of users ");
        return list;
    }
}
