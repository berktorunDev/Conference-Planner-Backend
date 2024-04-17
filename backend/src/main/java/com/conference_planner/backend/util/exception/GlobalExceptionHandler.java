package com.conference_planner.backend.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.conference_planner.backend.util.response.ResponseHandler;

/**
 * Global exception handler for the application using Lombok, catching
 * exceptions thrown by any controller.
 * Utilizes a custom ResponseHandler to ensure a consistent exception response
 * structure across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred: " + request.getDescription(false));
    }

    // Additional handlers for other types of exceptions can be added here.
}
