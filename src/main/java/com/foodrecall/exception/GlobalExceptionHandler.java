package com.foodrecall.exception;

//import java.util.HashMap;
//import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResponseNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return new ResponseEntity<>("Something went wrong : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<String> handleExternalApiError(ExternalApiException ex) {
        return new ResponseEntity<>("External API error: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionNotFound(SubscriptionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /*
     * @ExceptionHandler(MethodArgumentNotValidException.class)
     * public ResponseEntity<Map<String, String>>
     * handleValidationExceptions(MethodArgumentNotValidException ex) {
     * Map<String, String> errors = new HashMap<>();
     * 
     * ex.getBindingResult().getFieldErrors()
     * .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
     * 
     * return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
     * }
     */

}
