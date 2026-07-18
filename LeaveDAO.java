package com.ems.util;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$");
    }

    public static boolean isPositiveNumber(String value) {
        try {
            return Double.parseDouble(value) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
