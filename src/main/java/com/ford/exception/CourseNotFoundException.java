package com.ford.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CourseNotFoundException extends ResponseStatusException {
    public CourseNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}


