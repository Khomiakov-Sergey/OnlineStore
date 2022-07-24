package by.it.academy.ishop.services.user;

import by.it.academy.ishop.configurations.jwt.JwtProvider;
import by.it.academy.ishop.dtos.requests.UserRequestDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.entities.user.UserRole;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.UserAuthenticationException;
import by.it.academy.ishop.exceptions.UserRegistrationException;
import by.it.academy.ishop.mappers.UserMapper;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.repositories.user.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for users service")
public class UserApiServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private UserApiService userService;

    private User user;
    private UserRole userRole;

    @BeforeEach
    @Test
    void init() {
        userRole = UserRole.builder()
                .role(Role.ROLE_USER)
                .build();

        user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .password("test")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    @Test
    @DisplayName("User creation test for valid values in user fields")
    void checkResponseFor_CreateUser_MethodWhenAllFieldsAreValid() {

        User validUserFromDB = User.builder()
                .id(1L)
                .build();

        user.setUserRole(userRole);

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .password("test")
                .build();

        Mockito.when(userRoleRepository.findByRole(Role.ROLE_USER)).thenReturn(userRole);
        Mockito.when(passwordEncoder.encode(userRequestDto.getPassword())).thenReturn(userRequestDto.getPassword());
        Mockito.when(userRepository.save(user)).thenReturn(validUserFromDB);

        Assertions.assertEquals(validUserFromDB.getId(), userService.createUser(userRequestDto));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(userRoleRepository, Mockito.times(1)).findByRole(Role.ROLE_USER);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(userRequestDto.getPassword());
    }

    @Test
    @DisplayName("User creation test when user`s already existed in DB with such login or email")
    void checkResponseFor_CreateUser_MethodWhenUserWithSuchLoginOrEmailAlreadyExist() {

        user.setUserRole(userRole);

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .password("test")
                .build();

        Mockito.when(userRoleRepository.findByRole(Role.ROLE_USER)).thenReturn(userRole);
        Mockito.when(passwordEncoder.encode(userRequestDto.getPassword())).thenReturn(userRequestDto.getPassword());
        Mockito.when(userRepository.save(user)).thenThrow(new UserRegistrationException());

        Assertions.assertThrows(UserRegistrationException.class, () -> userService.createUser(userRequestDto));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(userRequestDto.getPassword());
        Mockito.verify(userRoleRepository, Mockito.times(1)).findByRole(Role.ROLE_USER);
    }

    @Test
    @DisplayName("User search test for valid id in user")
    void checkResponseFor_FindUserById_MethodWhenUserIdIsValid() {
        user.setId(2L);
        Long id = 2L;

        UserRespondDto userRespondDto = UserRespondDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .build();

        Mockito.when(userMapper.userToDto(user)).thenReturn(userRespondDto);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));

        Assertions.assertEquals(userRespondDto, userService.findUserById(id));

        Mockito.verify(userMapper, Mockito.times(1)).userToDto(user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("User search test for invalid id in user")
    void checkResponseFor_FindUserById_MethodWhenUserIdIsNotValid() {
        Long id = 2L;

        Mockito.when(userRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));
        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> userService.findUserById(id));

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("User search test for valid login in user")
    void checkResponseFor_FindUserByLogin_MethodWhenUserLoginIsValid() {
        String login = "Ivan";

        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Assertions.assertEquals(user, userService.findUserByLogin(login));

        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(login);
    }

    @Test
    @DisplayName("Token generate test for valid login and password user")
    void checkResponseFor_GetUserToken_MethodWhenUserLoginAndPasswordAreCorrect() {
        String login = "Iva";
        String password = "text";
        String token = "2dsad1233asd234@3D32";

        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Assertions.assertEquals(user, userService.findUserByLogin(login));

        Mockito.when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
        Mockito.when(jwtProvider.generateToken(login)).thenReturn(token);

        Assertions.assertEquals(token, userService.findUserByLoginAndPassword(login, password));

        Mockito.verify(userRepository, Mockito.times(2)).findByLogin(login);
        Mockito.verify(jwtProvider, Mockito.times(1)).generateToken(login);
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches(password, user.getPassword());
    }

    @Test
    @DisplayName("Token generate test for invalid authentication(login incorrect)")
    void checkResponseFor_GetUserToken_MethodWhenUserLoginIsNotCorrect() {
        String login = "Iva";
        String password = "text1";

        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        Assertions.assertThrows(UserAuthenticationException.class, () -> userService.findUserByLoginAndPassword(login, password));

        Mockito.verify(jwtProvider, Mockito.times(0)).generateToken(login);
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches(password, user.getPassword());
    }

    @Test
    @DisplayName("Token generate test for invalid authentication(password incorrect)")
    void checkResponseFor_GetUserToken_MethodWhenUserPasswordIsNotCorrect() {
        String login = "Ivan";
        String password = "test";

        Mockito.when(userRepository.findByLogin(login)).thenReturn(null);
        Assertions.assertThrows(UserAuthenticationException.class, () -> userService.findUserByLoginAndPassword(login, password));

        Mockito.verify(jwtProvider, Mockito.times(0)).generateToken(login);
        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(login);
    }

    @Test
    @DisplayName("User update test for valid user id and valid user fields ")
    void checkResponseFor_UpdateUser_MethodWhenUserIdIsExistInDBAndUserFieldsValuesAreValid() {
        Long id = 2L;
        user.setId(id);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .password("test")
                .build();

        Assertions.assertEquals(user.getId(), userService.updateUser(id, userRequestDto));

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("The test of finding all users, when some user have existed in DB")
    void checkResponseFor_GetAllUsers_MethodWhenDBHasSomeUsers() {

        Pageable pageable = PageRequest.of(0, 10);

        UserRespondDto userRespondDto = UserRespondDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@test.ru")
                .age(23)
                .login("Iva")
                .build();

        List<User> users = List.of(user);

        List<UserRespondDto> usersDto = List.of(userRespondDto);
        Page<User> userPage = new PageImpl<>(users);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(userPage);
        Mockito.when(userMapper.userToDto(user)).thenReturn(userRespondDto);

        Assertions.assertEquals(usersDto, userService.findAllUsers(pageable));

        Mockito.verify(userMapper, Mockito.times(1)).userToDto(user);
        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageable);

    }


}
