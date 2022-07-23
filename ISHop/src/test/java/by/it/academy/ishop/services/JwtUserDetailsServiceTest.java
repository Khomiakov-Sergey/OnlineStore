package by.it.academy.ishop.services;

import by.it.academy.ishop.configurations.JwtUser;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.entities.user.UserRole;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.services.user.JwtUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for jwt users service")
public class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    @DisplayName("Jwt user search test for valid login")
    void getJwtUserWhenUserLoginIsValid() {
        String login = "Iva";

        User user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .userRole(UserRole.builder()
                        .role(Role.ROLE_USER)
                        .build())
                .age(23)
                .email("test@test.ru")
                .login(login)
                .password("test")
                .build();

        JwtUser jwtUser1 = JwtUser.create(user);

        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Assertions.assertEquals(jwtUser1, jwtUserDetailsService.loadUserByUsername(login));

        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(login);
    }

    @Test
    @DisplayName("Jwt user search test for valid id in user")
    void throwExceptionWhenUserLoginIsNotValid() {
        String login = "Ivan1";
        Mockito.when(userRepository.findByLogin(login)).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(login));

        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(login);
    }
}
