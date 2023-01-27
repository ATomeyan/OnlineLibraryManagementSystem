package com.library.registration.validation;

/**
 * @author Artur Tomeyan
 * @date 18/11/2022
 */
public final class ValidationConstants {

    public static final String EMAIL_REGEXP = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$";

    private ValidationConstants() {
    }
}