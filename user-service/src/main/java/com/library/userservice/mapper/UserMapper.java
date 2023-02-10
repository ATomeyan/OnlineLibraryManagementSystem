package com.library.userservice.mapper;

import com.library.userservice.dto.user.UserRegistrationRequestDto;
import com.library.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Artur Tomeyan
 * @date 26.12.2022
 */
@Component
public final class UserMapper {

    private UserMapper() {
    }

    public User mapRegistrationRequestToEntity(UserRegistrationRequestDto userRegistrationRequestDto) {

        User user = new User();

        user.setId(userRegistrationRequestDto.getId());
        user.setFirstName(userRegistrationRequestDto.getFirstName());
        user.setLastName(userRegistrationRequestDto.getLastName());
        user.setEmail(userRegistrationRequestDto.getEmail());
        user.setUsername(userRegistrationRequestDto.getUsername());
        user.setEnabled(true);
        user.setCreateDate(LocalDate.now());

        return user;
    }
}