package com.library.userservice.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.userservice.dto.user.UserAuthenticationRequestDto;
import com.library.userservice.dto.user.UserAuthenticationResponseDto;
import com.library.userservice.dto.user.UserRegistrationRequestDto;
import com.library.userservice.entity.Role;
import com.library.userservice.entity.Roles;
import com.library.userservice.entity.User;
import com.library.userservice.entity.UserRoles;
import com.library.userservice.exceptions.NotValidException;
import com.library.userservice.exceptions.UserAlreadyExistException;
import com.library.userservice.exceptions.ObjectDoesntExistException;
import com.library.userservice.mapper.UserMapper;
import com.library.userservice.mapper.UserRolesMapper;
import com.library.userservice.repository.RoleRepository;
import com.library.userservice.repository.UserRepository;
import com.library.userservice.service.UserService;
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

        if (role == null) {
            LOGGER.error("Role doesn't exist.");
            throw new ObjectDoesntExistException("Role doesn't exist.");
        }

        User user = userMapper.mapRegistrationRequestToEntity(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));

        UserRoles userRoles = userRolesMapper.mapResponseDtoToEntity(user, role);
        user.setUserRoles(List.of(userRoles));

        userRepository.save(user);
        LOGGER.info("User successfully registered.");
    }

    @Override
    public UserAuthenticationResponseDto userAuthentication(UserAuthenticationRequestDto authenticationRequestDto) {

        if (authenticationRequestDto == null) {
            LOGGER.error("Authentication data is not valid.");
            throw new NotValidException("Authentication data is not valid");
        }

        String username = authenticationRequestDto.getUsername();
        String email = authenticationRequestDto.getEmail();

        Optional<User> user = userRepository.findUserByEmailOrUsernameAndEnabled(email, username);

        if (user.isEmpty()) {
            LOGGER.error("User by {}, {} doesn't found please register.", username, email);
            throw new ObjectDoesntExistException("User doesn't found please register.");
        }

        UserAuthenticationResponseDto userAuthenticationResponseDto = userMapper.mapUserEntityToAuthenticationResponse(user.get());

        return userAuthenticationResponseDto;
    }
}