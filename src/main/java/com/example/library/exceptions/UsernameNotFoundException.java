package com.example.library.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("Could not find username " + username);
    }
}