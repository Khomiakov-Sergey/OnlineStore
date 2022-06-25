package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
public class UserApiService implements UserService<User> {
    private final UserRepository<User> repository;
    private final Session session;

    public UserApiService(UserRepository<User> repository, Session session) {
        this.repository = repository;
        this.session = session;
    }

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

    @Override
    public void delete(Long id) {
    }

    @Override
    public void update(User User) {
    }

    @Override
    public Optional<User> getUser(String login, String password) {
        return repository.getUser(login, password);
    }

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
