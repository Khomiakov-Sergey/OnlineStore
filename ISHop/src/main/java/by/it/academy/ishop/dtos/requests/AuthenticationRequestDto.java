package by.it.academy.ishop.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO class for representing authentication request.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
public class AuthenticationRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
