package com.library.userservice.controller;

import com.library.userservice.dto.user.UserAuthenticationRequestDto;
import com.library.userservice.dto.user.UserAuthenticationResponseDto;
import com.library.userservice.dto.user.UserRegistrationRequestDto;
import com.library.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Artur Tomeyan
 * @date 24/01/2023
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registration(@RequestBody @Valid UserRegistrationRequestDto registrationRequestDto) {
        userService.register(registrationRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponseDto> login(@RequestBody @Valid UserAuthenticationRequestDto authenticationRequestDto) {
        UserAuthenticationResponseDto userAuthentication = userService.userAuthentication(authenticationRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(userAuthentication);
    }
}