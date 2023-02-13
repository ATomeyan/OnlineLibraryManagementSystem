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
    private String accessToken;
    private String refreshToken;
}