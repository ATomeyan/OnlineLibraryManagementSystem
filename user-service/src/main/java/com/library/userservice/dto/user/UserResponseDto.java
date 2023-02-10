package com.library.userservice.dto.user;

import lombok.Data;

import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 24/01/2023
 */
@Data
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}