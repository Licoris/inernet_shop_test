package ru.licoris.spring_internet_shop.exception;

public class ClientError extends RuntimeException {
    public ClientError(String message) {
        super(message);
    }
}
