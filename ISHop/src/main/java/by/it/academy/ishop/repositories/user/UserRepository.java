package by.it.academy.ishop.repositories.user;

import by.it.academy.ishop.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

}
