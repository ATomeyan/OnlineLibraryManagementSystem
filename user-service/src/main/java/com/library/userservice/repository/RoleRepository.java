package com.library.userservice.repository;

import com.library.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findRoleByRole(String role);
}