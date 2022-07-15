package by.it.academy.ishop.services.user;

import by.it.academy.ishop.configurations.JwtUser;
import by.it.academy.ishop.entities.user.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Service class for user authentication.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    /**
     * This method searches user by the transferred username(login) by using userService and create JwtUser
     * for authentication.
     * @param username - user unique login for authentication.
     * @return jwtUser - new JwtUser for authentication.
     */
    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username);
        return JwtUser.create(user);
    }
}
