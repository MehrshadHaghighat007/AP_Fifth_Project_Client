package org.example.controller;

public class AuthenticationManager {
    public static boolean signInAndSignUpChecking(String input) {
        return input.equals("1") || input.equals("2");
    }
}
