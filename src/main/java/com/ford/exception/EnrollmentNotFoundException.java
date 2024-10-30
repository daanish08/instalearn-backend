package com.ford.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EnrollmentNotFoundException extends ResponseStatusException {
    public EnrollmentNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}

