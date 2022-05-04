package by.it.academy.services.user;

import java.sql.SQLException;
import java.util.List;

public interface UserService<T> {
    void create(T user);

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String firstName, String secondName, int age, String login, String password);

    T getUser(String login, String password);

    List<T> getAllUsers() throws SQLException, ClassNotFoundException;
}
