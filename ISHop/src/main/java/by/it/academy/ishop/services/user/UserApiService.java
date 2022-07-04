package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.LoginRequest;
import by.it.academy.ishop.dtos.requests.UserDto;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.Status;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.mappers.UserMapper;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.repositories.user.UserRoleRepository;
import by.it.academy.ishop.repositories.user.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserRoleRepository userRoleRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return userMapper.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateUser(User user) {
        return null;
    }

    @Override
    public Long createUser(UserDto userDto) {
        final User user = buildNewUser(userDto);
        return userRepository.save(user).getId();
    }

    @Override
    public void checkEmailExist(String email) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    private User buildNewUser(UserDto request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .login(request.getLogin())
                .password(request.getPassword())
                .email(request.getEmail())
                .created_at(LocalDateTime.now())
                .userStatus(userStatusRepository.findByStatus(Status.NEW))
                .userRole(userRoleRepository.findByRole(Role.USER))
                .build();
    }
}
