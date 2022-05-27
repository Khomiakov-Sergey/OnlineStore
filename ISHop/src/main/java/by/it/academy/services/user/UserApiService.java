package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserApiService implements UserService<User> {
    private final UserRepository<User> repository;

    public UserApiService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.create(user);

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void update(int id, String firstName, String secondName, int age, String login, String password) {
    }

    @Override
    public Optional<User> getUser(String login, String password) {
        return repository.getUser(login, password);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }
}
