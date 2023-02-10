package com.library.userservice.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
@Data
@NoArgsConstructor
public class UserAuthenticationResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}