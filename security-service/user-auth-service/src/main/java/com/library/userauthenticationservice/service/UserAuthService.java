package com.library.userauthenticationservice.service;

import com.library.userauthenticationservice.model.UserAuthenticationRequest;
import com.library.userauthenticationservice.model.UserAuthenticationResponse;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
public interface UserAuthService {
    UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest);
}