package com.conference_planner.backend.model.dto.request;

import java.util.List;

import com.conference_planner.backend.model.dto.common.TalkDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

/**
 * Data Transfer Object for the request to schedule conference talks.
 * This class is designed to accept a list of TalkDTO objects that represents
 * multiple talks.
 * It ensures that the list of talks is not empty, making it mandatory to have
 * at least one talk in the request.
 */
public record ConferenceScheduleRequestDTO(
                @NotEmpty(message = "Talks list cannot be empty") // Ensures the list of talks is not empty
                @Valid // Ensures that each TalkDTO in the list is valid according to its own
                       // validation rules
                List<TalkDTO> talks) {
}