package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UserApiRepository implements UserRepository<User> {

    @Override
    public void create(User user) {
        Session session = DataSource.getInstance().getSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void update(int id, String firstName, String secondName, int ager, Map<String, String> credentials) {
    }

    @Override
    public Optional<User> getUser(String login, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = DataSource.getInstance().getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        session.close();
        return users;
    }
}
