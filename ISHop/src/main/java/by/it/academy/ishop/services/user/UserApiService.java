package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.UserDtoRequest;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.mappers.UserMapper;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.repositories.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDtoRespond getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return userMapper.userToDto(user);
    }

    @Override
    @Transactional
    public List<UserDtoRespond> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateUser(Long id, UserDtoRequest userDto) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user.getId();
    }

    @Override
    @Transactional
    public Long createUser(UserDtoRequest userDtoRequest) {
        final User user = buildNewUser(userDtoRequest);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User buildNewUser(UserDtoRequest userDtoRequest) {
        return User.builder()
                .firstName(userDtoRequest.getFirstName())
                .lastName(userDtoRequest.getLastName())
                .age(userDtoRequest.getAge())
                .login(userDtoRequest.getLogin())
                .password(userDtoRequest.getPassword())
                .email(userDtoRequest.getEmail())
                .created_at(LocalDateTime.now())
                .userRole(userRoleRepository.findByRole(Role.USER))
                .build();
    }
}
