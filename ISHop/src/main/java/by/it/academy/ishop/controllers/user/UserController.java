package by.it.academy.ishop.controllers.user;

import by.it.academy.ishop.dtos.UserDto;
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

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") @Valid Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@PathVariable ("id") @Valid  Long id, @RequestBody @Valid UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
