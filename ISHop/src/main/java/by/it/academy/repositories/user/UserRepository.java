package by.it.academy.repositories.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository<T> {

    void create(T user) throws SQLException, ClassNotFoundException;

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials);

    Optional<T> getUser(String name) throws SQLException, ClassNotFoundException;

    List<T> getAllUsers() throws SQLException, ClassNotFoundException;
}
