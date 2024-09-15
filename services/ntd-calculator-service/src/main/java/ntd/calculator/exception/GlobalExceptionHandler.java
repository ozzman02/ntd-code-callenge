package ntd.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ntd.calculator.constants.ApplicationConstants.ERROR;
import static ntd.calculator.constants.ApplicationConstants.ERRORS;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .toList();
        response.put(ERRORS, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> allExceptionsHandler(Exception ex) {
        return new ResponseEntity<>(getErrorsMap(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<?> errorResponseExceptionHandler(ErrorResponseException ex) {
        return new ResponseEntity<>(getErrorsMap(ex), ex.getHttpStatus());
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
