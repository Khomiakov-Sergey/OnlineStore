package by.it.academy.ishop.controllers.user;

import by.it.academy.ishop.dtos.requests.UserDto;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable @Valid Long id) {
        return userService.getUser(id);
    }


    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }


    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
