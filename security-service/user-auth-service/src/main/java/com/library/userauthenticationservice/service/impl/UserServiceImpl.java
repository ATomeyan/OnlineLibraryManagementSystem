package com.library.userauthenticationservice.service.impl;

import com.library.userauthenticationservice.model.UserAuthenticationRequest;
import com.library.userauthenticationservice.model.UserAuthenticationResponse;
import com.library.userauthenticationservice.service.UserService;
import com.library.userauthenticationservice.userclient.UserFeignClient;
import org.springframework.stereotype.Service;

/**
 * @author Artur Tomeyan
 * @date 10/02/2023
 */
@Service
public class UserServiceImpl implements UserService {

    private UserFeignClient userFeignClient;

    @Override
    public UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest) {

        UserAuthenticationResponse authenticate = userFeignClient.authenticate(userAuthenticationRequest);

        return authenticate;
    }
}