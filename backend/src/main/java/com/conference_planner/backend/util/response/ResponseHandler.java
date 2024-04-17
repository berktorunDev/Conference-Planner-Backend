package com.conference_planner.backend.util.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Utility class to handle API responses in a standardized format.
 * Provides methods to create success and error responses.
 */
@Component
public class ResponseHandler {

    /**
     * Creates a success response with given data, status, and an informational
     * message.
     * Adds a 'dataCount' field when the data is a list to show the number of items.
     * 
     * @param status      HTTP status of the response.
     * @param infoMessage Informative message to be included in the response.
     * @param data        The actual data object to be included in the response.
     * @return ResponseEntity containing the response details.
     */
    public static ResponseEntity<Object> successResponse(HttpStatus status, String infoMessage, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        result.put("infoMessage", infoMessage);
        addDataCountIfNeeded(data, result);
        return new ResponseEntity<>(result, status);
    }

    /**
     * Creates an error response with the given status and error message.
     * 
     * @param status       HTTP status of the response.
     * @param errorMessage The error message explaining what went wrong.
     * @return ResponseEntity containing the error details.
     */
    public static ResponseEntity<Object> errorResponse(HttpStatus status, String errorMessage) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("errorMessage", errorMessage);
        return new ResponseEntity<>(result, status);
    }

    /**
     * Adds a 'dataCount' field to the result map if the data object is an instance
     * of List.
     * 
     * @param data   The data object, potentially a list.
     * @param result The map where 'dataCount' should be added if applicable.
     */
    private static void addDataCountIfNeeded(Object data, Map<String, Object> result) {
        if (data instanceof List<?>) {
            List<?> dataList = (List<?>) data;
            result.put("dataCount", dataList.size());
        }
    }
}
