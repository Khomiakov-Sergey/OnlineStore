package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    Long updateUser(Long id, UserDto user);

    Long createUser(UserDto userDto);

    void deleteUser(Long id);


}
