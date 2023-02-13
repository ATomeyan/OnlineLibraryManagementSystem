package com.library.userauthenticationservice.service.impl;

import com.library.userauthenticationservice.exceptions.ObjectNotFoundException;
import com.library.userauthenticationservice.exceptions.ObjectNotValidException;
import com.library.userauthenticationservice.model.UserAuthenticationRequest;
import com.library.userauthenticationservice.model.UserAuthenticationResponse;
import com.library.userauthenticationservice.service.UserAuthService;
import com.library.userauthenticationservice.userclient.UserFeignClient;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Artur Tomeyan
 * @date 10/02/2023
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserAuthService.class);
    private final UserFeignClient userFeignClient;

    public UserAuthServiceImpl(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest) {

        if (userAuthenticationRequest == null) {
            LOGGER.error("User data is not valid.");
            throw new ObjectNotValidException("User data is not valid.");
        }

        UserAuthenticationResponse authenticate = userFeignClient.authenticate(userAuthenticationRequest);

        if (authenticate == null) {
            LOGGER.error("User doesn't found.");
            throw new ObjectNotFoundException("User doesn't found.");
        }

        return authenticate;
    }
}