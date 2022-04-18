package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class UserApiService implements UserService<User> {
    private final UserRepository<User> repository;

    private final static Logger log = Logger.getLogger(UserApiService.class);

    public UserApiService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        try {
            repository.create(user);
        } catch (SQLException | ClassNotFoundException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        repository.delete(id);
    }

    @Override
    public void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials) {

    }

    @Override
    public Optional<User> getUser(String login) throws SQLException, ClassNotFoundException {
        return Optional.ofNullable(repository.getUser(login)
                .orElseThrow(() -> new NoSuchElementException("User with secondName " + login + " is not exists")));
    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        try {
            return repository.getAllUsers();
        } catch (SQLException | ClassNotFoundException e) {
            log.info(e.getMessage());
        }
        return Collections.emptyList();
    }
}
