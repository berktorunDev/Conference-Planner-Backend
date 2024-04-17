package com.conference_planner.backend.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.conference_planner.backend.model.dto.common.TalkDTO;
import com.conference_planner.backend.model.dto.request.ConferenceScheduleRequestDTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class ConferenceScheduleRequestDTOTest {

    private static Validator validator;

    @Test
    @DisplayName("ConferenceScheduleRequestDTO should not accept an empty list of talks")
    void testConferenceScheduleRequestDTO_EmptyList() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        ConferenceScheduleRequestDTO request = new ConferenceScheduleRequestDTO(Collections.emptyList());
        var constraintViolations = validator.validate(request);

        assertFalse(constraintViolations.isEmpty(), "There should be a constraint violation for empty talks list");
        assertTrue(constraintViolations.stream().anyMatch(v -> v.getMessage().contains("Talks list cannot be empty")),
                "Error message should indicate that the talks list cannot be empty");
    }

    @Test
    @DisplayName("ConferenceScheduleRequestDTO should accept a non-empty list of talks")
    void testConferenceScheduleRequestDTO_NonEmptyList() {
        List<TalkDTO> talks = List.of(new TalkDTO("Valid Talk", "30min"));
        ConferenceScheduleRequestDTO request = new ConferenceScheduleRequestDTO(talks);
        var constraintViolations = validator.validate(request);

        assertTrue(constraintViolations.isEmpty(),
                "No constraints should be violated when the talks list is not empty");
    }
}
