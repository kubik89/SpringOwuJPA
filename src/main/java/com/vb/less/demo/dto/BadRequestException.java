package com.vb.less.demo.dto;

// це власний Exception Для будь якого методу
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
