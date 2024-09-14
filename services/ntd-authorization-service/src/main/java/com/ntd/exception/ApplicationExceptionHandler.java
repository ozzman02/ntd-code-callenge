package com.ntd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.ntd.constants.ApplicationConstants.ERROR;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> allExceptionsHandler(Exception ex) {
        return new ResponseEntity<>(getErrorsMap(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<?> errorResponseExceptionHandler(ErrorResponseException ex) {
        return new ResponseEntity<>(getErrorsMap(ex), ex.getHttpStatus());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
        return new ResponseEntity<>(getErrorsMap(ex), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationExceptionHandler(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getValidationErrors());
    }

    private Map<String, String> getErrorsMap(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR, ex.getMessage());
        return errors;
    }
}
