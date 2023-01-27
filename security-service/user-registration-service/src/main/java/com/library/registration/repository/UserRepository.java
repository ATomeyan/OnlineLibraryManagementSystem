package com.library.registration.repository;

import com.library.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("from User u where u.email = :#{#email} or u.username = :#{#username} and u.enabled = true")
    Optional<User> findUserByEmailOrUsernameAndEnabled(String email, String username);
}