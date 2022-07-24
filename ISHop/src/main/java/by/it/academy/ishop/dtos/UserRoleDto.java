package by.it.academy.ishop.dtos;

import by.it.academy.ishop.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * DTO class for representing user role.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    @Enumerated(EnumType.STRING)
    private Role role;
}
