package com.library.registration.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.registration.dto.user.UserRegistrationRequestDto;
import com.library.registration.entity.Role;
import com.library.registration.entity.Roles;
import com.library.registration.entity.User;
import com.library.registration.entity.UserRoles;
import com.library.registration.exceptions.NotValidException;
import com.library.registration.exceptions.UserAlreadyExistException;
import com.library.registration.mapper.UserMapper;
import com.library.registration.mapper.UserRolesMapper;
import com.library.registration.repository.RoleRepository;
import com.library.registration.repository.UserRepository;
import com.library.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 23/01/2023
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRolesMapper userRolesMapper;

    @Override
    @Transactional
    public void register(UserRegistrationRequestDto registrationRequestDto) {

        if (registrationRequestDto == null) {
            LOGGER.error("User data is not valid.");
            throw new NotValidException("User data is not valid.");
        }

        String email = registrationRequestDto.getEmail();
        String username = registrationRequestDto.getUsername();

        Optional<User> userByEmailOrUsernameAndEnabled = userRepository.findUserByEmailOrUsernameAndEnabled(email, username);

        if (userByEmailOrUsernameAndEnabled.isPresent()) {
            LOGGER.error("User by email {} or username {} already registered:", email, username);
            throw new UserAlreadyExistException("User by email or username already registered.");
        }

        Role role = roleRepository.findRoleByRole(Roles.USER.getRole());

        User user = userMapper.mapRegistrationRequestToEntity(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));

        UserRoles userRoles = userRolesMapper.mapResponseDtoToEntity(user, role);
        user.setUserRoles(List.of(userRoles));

        userRepository.save(user);
        LOGGER.info("User successfully registered.");
    }
}