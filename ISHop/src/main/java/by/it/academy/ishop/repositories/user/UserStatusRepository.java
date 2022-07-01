package by.it.academy.ishop.repositories.user;

import by.it.academy.ishop.entities.user.Status;
import by.it.academy.ishop.entities.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    UserStatus findByStatus(Status status);
}
