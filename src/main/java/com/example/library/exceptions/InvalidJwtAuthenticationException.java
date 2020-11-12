package com.example.library.exceptions;

import org.springframework.security.core.AuthenticationException;

//@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Expired or invalid JWT token")
public class InvalidJwtAuthenticationException extends AuthenticationException {
	public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
