package by.it.academy.repositories.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository<T> {

    void create(T user) ;

    void delete(int id) throws SQLException, ClassNotFoundException;

    void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials);

    Optional<T> getUser(String name, String password);

    List<T> getAllUsers();
}
