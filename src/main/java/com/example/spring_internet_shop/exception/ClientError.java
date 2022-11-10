package com.example.spring_internet_shop.exception;

public class ClientError extends RuntimeException {
    public ClientError(String message) {
        super(message);
    }
}
