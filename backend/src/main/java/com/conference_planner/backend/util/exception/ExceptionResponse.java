package com.conference_planner.backend.util.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Standard exception response structure for API responses using Lombok to
 * reduce boilerplate code.
 * Includes a message, detailed errors, a status code, and the timestamp of the
 * occurrence.
 */
@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final String details;
    private final HttpStatus status;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
