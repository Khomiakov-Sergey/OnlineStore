package by.it.academy.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private int id;
    private final String firstName;
    private final String secondName;
    private UserType userType;
    private final int age;
    private final String login;
    private final String password;

}
