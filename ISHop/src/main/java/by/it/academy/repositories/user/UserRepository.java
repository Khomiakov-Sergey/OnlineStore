package by.it.academy.repositories.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T> {

    void create(T user);

    void delete(int id);

    void update(User user);

    Optional<T> getUser(String name, String password);

    List<T> getAllUsers();
}
