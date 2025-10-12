package com.app1.src;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.*;

@ControllerAdvice   // <-- tells Spring this handles exceptions globally
public class GlobalExceptionHandler {

    // Handle validation errors from @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        // Extract all error messages
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .toList();

        // Prepare a simple response body
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * It hanlde all thoes exception which will not handle during the code and throw away
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidJson(HttpMessageNotReadableException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Invalid JSON format");

        // Optional: extract the short cause message (without stack trace)
        String message = ex.getMostSpecificCause() != null 
            ? ex.getMostSpecificCause().getMessage()
            : ex.getMessage();

        // Clean the message a bit (optional)
        if (message != null && message.length() > 200) {
            message = message.substring(0, 200) + "...";
        }

        body.put("details", message);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
