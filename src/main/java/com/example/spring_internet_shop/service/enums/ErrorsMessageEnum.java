package com.example.spring_internet_shop.service.enums;

public enum ErrorsMessageEnum {
    NOT_FOUND("Entity not found"), BAD_POST_ID_REQUEST("Entity id not allowed here"),
    STATUS_ALREADY_PAID("Order paid");

    private final String message;

    ErrorsMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
