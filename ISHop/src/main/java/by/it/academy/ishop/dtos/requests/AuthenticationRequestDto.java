package by.it.academy.ishop.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
