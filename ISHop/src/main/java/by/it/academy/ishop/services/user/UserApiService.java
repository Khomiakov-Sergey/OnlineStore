package by.it.academy.ishop.services.user;

import by.it.academy.ishop.configurations.jwt.JwtProvider;
import by.it.academy.ishop.dtos.requests.UserRequestDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.UserAuthenticationException;
import by.it.academy.ishop.exceptions.UserRegistrationException;
import by.it.academy.ishop.mappers.UserMapper;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.repositories.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class for users with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private static final String INCORRECT_AUTHENTICATION = "Login or password incorrect";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private final UserMapper userMapper;

    /**
     * This method searches user by the transferred id by using UserRepository.
     * If it doesn`t exist -> throw EntityByIdNotFoundException.
     * @param id - User identifier.
     * @return UserRespondDto - User representation without password.
     */
    @Override
    @Transactional
    public UserRespondDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        return userMapper.userToDto(user);
    }

    /**
     * This method searches user by the transferred login by using UserRepository.
     * Intermediate method.
     * @param login - User unique login for authentication.
     * @return User - User representation.
     */
    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * This method searches user by the transferred login and password by using UserRepository.
     * If login or password incorrect -> throw UserAuthenticationException.
     * Intermediate method.
     * @param login - User unique login for authentication.
     * @param password - User unique password for authentication.
     * @return User - User representation.
     */
    @Override
    @Transactional
    public String findUserByLoginAndPassword(String login, String password) {
        User user = findUserByLogin(login);
        if (Objects.isNull(user) || !passwordEncoder.matches(password, user.getPassword())) {
            throw new UserAuthenticationException(INCORRECT_AUTHENTICATION);
        }
        return jwtProvider.generateToken(user.getLogin());
    }

    /**
     * This method searches all users by using UserRepository.
     * @return List<UserRespondDto> - List of users without passwords.
     */
    @Override
    @Transactional
    public List<UserRespondDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method searches user by the transferred id and updates information by using UserRepository.
     * If it doesn`t exist -> throw EntityByIdNotFoundException.
     * @param id - User identifier.
     * @param userRequestDto - User information from request.
     * @return id - User identifier.
     */
    @Override
    @Transactional
    public Long updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        return user.getId();
    }

    /**
     * This method creates new user and transfers it in UserRepository to save.
     * @param userRequestDto - User information from request.
     * @return id - User identifier.
     */
    @Override
    @Transactional
    public Long createUser(UserRequestDto userRequestDto) {
        final User user = buildNewUser(userRequestDto);
        return userRepository.save(user).getId();
    }

    /**
     * This method searches user by the transferred id and deletes him by using UserRepository, if he is existed.
     * @param id - User identifier.
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * This intermediate method helps main method <>createUser</> build user from request.
     * If user tries to register with not unique login or password -> throw UserRegistrationException.
     * @param userRequestDto - User information from request.
     * @return user - New User.
     */
    private User buildNewUser(UserRequestDto userRequestDto) {
        User userWithSuchLogin = findUserByLogin(userRequestDto.getLogin());
        User userWithSuchEmail = userRepository.findByEmail(userRequestDto.getEmail());

        if (Objects.nonNull(userWithSuchLogin) || Objects.nonNull(userWithSuchEmail)) {
            throw new UserRegistrationException();
        }

        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .age(userRequestDto.getAge())
                .login(userRequestDto.getLogin())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .email(userRequestDto.getEmail())
                .created_at(LocalDateTime.now())
                .userRole(userRoleRepository.findByRole(Role.ROLE_USER))
                .build();
    }
}
