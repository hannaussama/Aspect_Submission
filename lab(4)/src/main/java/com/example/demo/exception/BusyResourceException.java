package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class BusyResourceException extends RuntimeException {
    public BusyResourceException(String msg) {
        super(msg);
    }
}
