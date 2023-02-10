package com.library.userservice.entity;

/**
 * @author Artur Tomeyan
 * @date 25/01/2023
 */
public enum Roles {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}