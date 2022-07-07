package by.it.academy.ishop.controllers.user;

import by.it.academy.ishop.dtos.requests.UserDtoRequest;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import by.it.academy.ishop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid UserDtoRequest userDtoRequest) {
        return userService.createUser(userDtoRequest);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoRespond getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDtoRespond> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDtoRequest userDtoRequest) {
        return userService.updateUser(id, userDtoRequest);
    }
}
