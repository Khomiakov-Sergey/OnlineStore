package by.it.academy.ishop.services.user;

import by.it.academy.ishop.configurations.JwtUser;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Service class for user authentication.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * This method searches user by the transferred username(login) by using userService and create JwtUser
     * for authentication.
     * @param username - user unique login for authentication.
     * @return jwtUser - new JwtUser for authentication.
     */
    @Override
    @Transactional
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("User is not exist in DB");
        }
        return JwtUser.create(user);
    }
}
