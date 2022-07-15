package by.it.academy.ishop.dtos.requests;

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
public class UserRequestDto {
    @Positive
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private int age;
    @Email
    private String email;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private UserRoleDto role;

}
