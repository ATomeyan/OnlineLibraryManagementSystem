package com.library.registration.mapper;

import com.library.registration.entity.Role;
import com.library.registration.entity.User;
import com.library.registration.entity.UserRoles;
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