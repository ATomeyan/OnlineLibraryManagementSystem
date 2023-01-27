package com.library.registration.controller;

import com.library.registration.dto.user.UserRegistrationRequestDto;
import com.library.registration.service.UserService;
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
@RequestMapping("/api/v1/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> registration(@RequestBody @Valid UserRegistrationRequestDto registrationRequestDto) {
        userService.register(registrationRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}