package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This service class is responsible for transferred users information from controllers to repository layer.
 * It contains business logic and opens transaction for working in session.
 */
@Log4j
public class UserApiService implements UserService<User> {
    private final UserRepository<User> repository;
    private final Session session;

    public UserApiService(UserRepository<User> repository, Session session) {
        this.repository = repository;
        this.session = session;
    }

    /**
     * This method opens transaction, gets the user from the parameter and sends it to the repository layer for
     * creating a new user. After that transaction will be commited.
     */
    @Override
    public void create(User user) {
        try {
            session.beginTransaction();
            repository.create(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }

    }

    /**
     * This method opens transaction, gets the user id from the parameter and sends it to the repository layer for
     * deleting the user. After that transaction will be commited. The method is not implemented.
     */
    @Override
    @Transactional
    public void delete(Long id) {
    }

    /**
     * This method opens transaction, gets the user from the parameter and sends it to the repository layer for
     * updating the user. After that transaction will be commited. The method is not implemented.
     */
    @Override
    @Transactional
    public void update(User User) {
    }

    /**
     * This method gets the login and password from the parameter and sends it to the repository layer for
     * searching the user.
     *
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUser(String login, String password) {
        return repository.getUser(login, password);
    }

    /**
     * This method opens transaction, gets all users from repository layer. After that transaction will be commited.
     *
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            users = repository.getAllUsers();
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.info(ex);
            session.getTransaction().rollback();
        }
        return users;

    }


}
