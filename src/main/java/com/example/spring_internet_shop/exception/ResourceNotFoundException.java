package com.example.spring_internet_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ClientError {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
