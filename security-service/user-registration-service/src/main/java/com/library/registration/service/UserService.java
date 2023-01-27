package com.library.registration.service;

import com.library.registration.dto.user.UserRegistrationRequestDto;

/**
 * @author Artur Tomeyan
 * @date 23/01/2023
 */
public interface UserService {

    void register(UserRegistrationRequestDto registrationRequestDto);
}