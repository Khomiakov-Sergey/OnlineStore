package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
