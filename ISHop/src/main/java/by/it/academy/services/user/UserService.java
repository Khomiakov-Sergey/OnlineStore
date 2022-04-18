package by.it.academy.services.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService<T> {
    void create(T user);

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials);

    Optional<T> getUser(String name) throws SQLException, ClassNotFoundException;

    List<T> getAllUsers() throws SQLException, ClassNotFoundException;
}
