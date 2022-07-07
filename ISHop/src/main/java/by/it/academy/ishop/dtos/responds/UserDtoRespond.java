package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRespond {
    @Positive
    private Long id;
    @NotBlank
    private String login;
    private UserRoleDto role;
}
