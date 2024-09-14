package com.ntd.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponseException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;

    public ErrorResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
