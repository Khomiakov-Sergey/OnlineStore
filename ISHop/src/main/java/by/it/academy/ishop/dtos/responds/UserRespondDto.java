package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO class for representing user respond.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespondDto {
    private Long id;
    private String login;
    private UserRoleDto role;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private LocalDateTime createdAt;
}
