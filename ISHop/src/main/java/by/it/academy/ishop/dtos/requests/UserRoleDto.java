package by.it.academy.ishop.dtos.requests;

import by.it.academy.ishop.entities.user.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRoleDto {
    @Enumerated(EnumType.STRING)
    Role role;
}
