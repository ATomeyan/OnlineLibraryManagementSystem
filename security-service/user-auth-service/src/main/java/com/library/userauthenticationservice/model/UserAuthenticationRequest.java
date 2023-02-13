package com.library.userauthenticationservice.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
@Data
public class UserAuthenticationRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}