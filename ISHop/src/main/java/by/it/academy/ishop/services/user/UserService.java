package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.RegistrationUserRequest;
import by.it.academy.ishop.entities.user.User;

public interface UserService {
    User getUser(Long id);
    Long updateUser(User user);
    Long createUser(RegistrationUserRequest registrationUserRequest);

    void checkEmailExist(String email);
    void deleteUser(Long id);




}
