package com.library.registration.dto.user;

import com.library.registration.validation.ValidationConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDto {

    private UUID id;
    @NotNull
    @Size(max = 255)
    private String firstName;
    @NotNull
    @Size(max = 255)
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = ValidationConstants.EMAIL_REGEXP)
    @Size(max = 255)
    private String email;
    @NotNull
    @Size(max = 255)
    private String username;
    @NotNull
    @Pattern(message = "Password is not valid", regexp = ValidationConstants.PASSWORD_REGEXP)
    @Size(min = 8, max = 12)
    private String password;
}