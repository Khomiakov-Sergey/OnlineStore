package by.it.academy.ishop.controllers;


import by.it.academy.ishop.dtos.requests.RegistrationUserRequest;
import by.it.academy.ishop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid RegistrationUserRequest registrationUserRequest){
        return userService.createUser(registrationUserRequest);
    }
}
