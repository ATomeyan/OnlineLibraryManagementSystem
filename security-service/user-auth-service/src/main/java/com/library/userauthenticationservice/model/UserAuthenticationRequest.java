package com.library.userauthenticationservice.model;

import lombok.Data;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
@Data
public class UserAuthenticationRequest {
    private String username;
    private String password;
}