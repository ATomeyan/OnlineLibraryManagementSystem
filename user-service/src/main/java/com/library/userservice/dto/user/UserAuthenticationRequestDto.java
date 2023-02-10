package com.library.userservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationRequestDto {

    @NotNull
    @NotEmpty
    private String username;

    private String email;

    @NotEmpty
    @NotNull
    private String password;
}