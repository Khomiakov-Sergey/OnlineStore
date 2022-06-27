package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * This repository class is responsible for transferred user information from services to DB using by hibernate.
 */
public class UserApiRepository implements UserRepository<User> {

    private final Session session;

    public UserApiRepository(Session session) {
        this.session = session;
    }

    /**
     * This method persists user using by hibernate.
     */
    @Override
    public void create(User user) {
        session.persist(user);
    }

    /**
     * This method deletes user using by hibernate. The method is not implemented.
     */
    @Override
    public void delete(int id) {
    }

    /**
     * This method updates user using by hibernate. The method is not implemented.
     */
    @Override
    public void update(User user) {
    }

    /**
     * This method gets user using by hibernate and streams.
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUser(String login, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    /**
     * This method gets all users using by criteria and hibernate.
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        userQuery.select(userRoot);
        return session.createQuery(userQuery).getResultList();
    }
}
