package by.it.academy.ishop.controllers.user;

import by.it.academy.ishop.dtos.requests.AuthenticationRequestDto;
import by.it.academy.ishop.dtos.requests.UserRequestDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for users. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * This method gets userRequestDto and tries to register user with using service layer.
     * For all roles.
     * @param userRequestDto - User information from request.
     * @return id - User identifier.
     */
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public Long registerUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    /**
     * This method gets AuthenticationRequestDto and tries to authenticate user with using service layer.
     * For all roles.
     * @param request - AuthenticationRequestDto with login and password.
     * @return token - unique token for authentication users in api.
     */
    @PostMapping("auth")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody @Valid AuthenticationRequestDto request) {
        return userService.findUserByLoginAndPassword(request.getLogin(), request.getPassword());
    }

    /**
     * This method gets user identifier and tries to find user with using service layer.
     * For role "ADMIN"
     * @param id - User identifier.
     * @return UserRespondDto - User representation without password.
     */
    @GetMapping("admin/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRespondDto getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    /**
     * This method gets all users with using service layer.
     * For role "ADMIN"
     * @return List<UserRespondDto> - List of users without passwords.
     */
    @GetMapping("admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRespondDto> getUsers(@PageableDefault(size = 8) @SortDefault(sort = "lastName") Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    /**
     * This method gets user identifier and tries to delete user with using service layer.
     * For role "ADMIN"
     * @param id - User identifier.
     */
    @DeleteMapping("admin/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    /**
     * This method gets user identifier, userRequestDto and tries to update user with using service layer.
     * For role "ADMIN"
     * @param id             - User identifier.
     * @param userRequestDto - User information from request.
     * @return id - User identifier.
     */
    @PutMapping("admin/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }
}
