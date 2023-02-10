package com.library.userauthenticationservice.controller;

import com.library.userauthenticationservice.model.UserAuthenticationRequest;
import com.library.userauthenticationservice.model.UserAuthenticationResponse;
import com.library.userauthenticationservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserAuthenticationResponse> authenticate(@RequestBody UserAuthenticationRequest userAuthenticationRequest){
        UserAuthenticationResponse login = userService.login(userAuthenticationRequest);

        return ResponseEntity.status(HttpStatus.OK).body(login);
    }
}