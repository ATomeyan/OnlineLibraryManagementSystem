package com.library.userauthenticationservice.model;

import lombok.Data;

import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
@Data
public class UserAuthenticationResponse {
    private UUID id;
    private String accessToken;
    private String refreshToken;
}