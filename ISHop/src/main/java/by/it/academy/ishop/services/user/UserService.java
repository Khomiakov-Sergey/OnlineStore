package by.it.academy.ishop.services.user;

import by.it.academy.ishop.dtos.requests.UserDtoRequest;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;

import java.util.List;

public interface UserService {

    UserDtoRespond getUser(Long id);

    List<UserDtoRespond> getUsers();

    Long updateUser(Long id, UserDtoRequest userDtoRequest);

    Long createUser(UserDtoRequest userDtoRequest);

    void deleteUser(Long id);


}
