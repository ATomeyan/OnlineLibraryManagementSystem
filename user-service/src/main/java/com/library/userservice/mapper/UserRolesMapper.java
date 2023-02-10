package com.library.userservice.mapper;

import com.library.userservice.entity.Role;
import com.library.userservice.entity.User;
import com.library.userservice.entity.UserRoles;
import org.springframework.stereotype.Component;

/**
 * @author Artur Tomeyan
 * @date 25/01/2023
 */
@Component
public final class UserRolesMapper {

    private UserRolesMapper() {
    }

    public UserRoles mapResponseDtoToEntity(User user, Role role) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);

        return userRoles;
    }
}