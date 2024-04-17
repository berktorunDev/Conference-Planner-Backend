package com.conference_planner.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference_planner.backend.model.dto.request.ConferenceScheduleRequestDTO;
import com.conference_planner.backend.service.ConferenceScheduleService;
import com.conference_planner.backend.util.response.ResponseHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling conference scheduling requests.
 * This class manages the routing and processing of POST requests to schedule
 * conference talks.
 */
@RestController
@RequestMapping("/api/conference")
@RequiredArgsConstructor
public class ConferenceScheduleController {

    private final ConferenceScheduleService scheduleService;

    /**
     * Schedules conference talks based on the provided request data.
     * This method relies on the ConferenceScheduleService to process the request
     * and
     * determine the appropriate scheduling of talks. In case of successful
     * processing,
     * a success response is returned. Any exceptions or errors during processing
     * are handled globally by the GlobalExceptionHandler rather than locally within
     * this method.
     * This approach ensures that all exceptions across the application are handled
     * consistently
     * and reduces code duplication.
     *
     * @param scheduleRequest the request containing the details of talks to be
     *                        scheduled.
     * @return ResponseEntity containing the scheduled talks and a success message.
     */
    @PostMapping("/schedule")
    public ResponseEntity<Object> scheduleConference(@RequestBody @Valid ConferenceScheduleRequestDTO scheduleRequest) {
        var scheduledTalks = scheduleService.scheduleConference(scheduleRequest);
        return ResponseHandler.successResponse(HttpStatus.OK, "Conference scheduled successfully", scheduledTalks);
    }
}
