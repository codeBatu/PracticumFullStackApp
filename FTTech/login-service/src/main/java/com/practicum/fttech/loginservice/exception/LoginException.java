package com.practicum.fttech.loginservice.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
 private HttpStatus status;
    public LoginException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
