package com.library.userservice.service;

import com.library.userservice.dto.user.UserAuthenticationRequestDto;
import com.library.userservice.dto.user.UserAuthenticationResponseDto;
import com.library.userservice.dto.user.UserRegistrationRequestDto;

/**
 * @author Artur Tomeyan
 * @date 23/01/2023
 */
public interface UserService {

    void register(UserRegistrationRequestDto registrationRequestDto);

    UserAuthenticationResponseDto userAuthentication(UserAuthenticationRequestDto authenticationRequestDto);
}