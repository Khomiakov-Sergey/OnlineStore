package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public class UserApiRepository implements UserRepository<User> {

    private final Session session;

    public UserApiRepository(Session session) {
        this.session = session;
    }

    @Override
    public void create(User user) {
        session.save(user);
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void update(User user) {
    }

    @Override
    public Optional<User> getUser(String login, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        userQuery.select(userRoot);
        return session.createQuery(userQuery).getResultList();
    }
}
