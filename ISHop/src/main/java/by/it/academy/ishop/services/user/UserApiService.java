package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.RegistrationUserRequest;
import by.it.academy.ishop.entities.user.Role;
import by.it.academy.ishop.entities.user.Status;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.repositories.user.UserRepository;
import by.it.academy.ishop.repositories.user.UserRoleRepository;
import by.it.academy.ishop.repositories.user.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public Long updateUser(User user) {
        return null;
    }

    @Override
    public Long createUser(RegistrationUserRequest registrationUserRequest) {
        final User user = buildNewUser(registrationUserRequest);
        return userRepository.save(user).getId();
    }

    @Override
    public void checkEmailExist(String email) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    private User buildNewUser(RegistrationUserRequest request) {
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
