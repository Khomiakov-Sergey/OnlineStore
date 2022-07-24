package by.it.academy.ishop.repositories.user;

import by.it.academy.ishop.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByLogin(String login);

}
