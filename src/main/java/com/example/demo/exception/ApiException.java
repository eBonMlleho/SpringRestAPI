package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private ZonedDateTime timeStamp;
    private String testMessage = "This is from custom exception";

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }
}
