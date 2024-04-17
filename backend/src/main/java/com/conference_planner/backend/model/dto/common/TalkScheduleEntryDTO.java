package com.conference_planner.backend.model.dto.common;

/**
 * Represents a single scheduled entry in a conference track, including time and
 * talk title.
 * Time is stored as a formatted string (e.g., "09:00 AM").
 */
public record TalkScheduleEntryDTO(String time, String title) {
}
