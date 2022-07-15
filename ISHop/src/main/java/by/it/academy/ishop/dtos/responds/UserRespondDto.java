package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
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
}
