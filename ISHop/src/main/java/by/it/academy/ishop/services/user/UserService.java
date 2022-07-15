package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.UserRequestDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.user.User;

import java.util.List;

public interface UserService {

    UserRespondDto findUserById(Long id);

    User findUserByLogin(String login);

    String findUserByLoginAndPassword(String login, String password);

    List<UserRespondDto> findAllUsers();

    Long updateUser(Long id, UserRequestDto userRequestDto);

    Long createUser(UserRequestDto userRequestDto);

    void deleteUser(Long id);


}
