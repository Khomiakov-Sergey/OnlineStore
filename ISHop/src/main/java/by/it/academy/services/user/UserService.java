package by.it.academy.services.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService<T> {
    void create(T user);

    void delete(Long id);

    void update(User user);

    Optional<T> getUser(String login, String password);

    List<T> getAllUsers();
}
