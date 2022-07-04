package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.LoginRequest;
import by.it.academy.ishop.dtos.requests.UserDto;
import by.it.academy.ishop.entities.user.User;

import java.util.List;

public interface UserService {
    UserDto getUser(Long id);
    List<UserDto> getAllUsers();
    Long updateUser(User user);
    Long createUser(UserDto userDto);

    void checkEmailExist(String email);
    void deleteUser(Long id);




}
