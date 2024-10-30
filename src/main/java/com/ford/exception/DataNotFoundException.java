package com.ford.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataNotFoundException extends ResponseStatusException {
    public DataNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}