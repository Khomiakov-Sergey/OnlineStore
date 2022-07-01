package by.it.academy.ishop.repositories.user;

import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(Role role);
}
