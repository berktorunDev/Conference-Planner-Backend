package com.conference_planner.backend.model.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object for talks.
 * This class encapsulates the data for a single talk which includes its title
 * and duration.
 * Duration can either be specified in minutes or marked as a lightning talk.
 */
public record TalkDTO(
        @NotBlank(message = "Title cannot be empty") // Ensures the title is not blank
        String title,
        @Pattern(regexp = "^((\\d+min)|lightning)$", message = "Duration must be either a number followed by 'min' or 'lightning'") // Validates
                                                                                                                                    // the
                                                                                                                                    // format
                                                                                                                                    // of
                                                                                                                                    // the
                                                                                                                                    // duration
        String duration) {

    /**
     * Converts the duration of the talk from its string format to an integer
     * representing the total minutes.
     * Handles 'lightning' talks specifically by returning a constant value.
     * 
     * @return the duration in minutes.
     */
    public int getDurationInMinutes() {
        if ("lightning".equals(this.duration)) {
            return 5;
        } else {
            return Integer.parseInt(this.duration.replace("min", ""));
        }
    }
}
