package com.conference_planner.backend.model.dto.response;

import java.util.List;
import java.util.Map;

import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;

/**
 * Response DTO for delivering a structured schedule of a conference.
 * Organizes tracks as an array of maps, each map containing a label and its
 * events.
 */
public record ConferenceResponseDTO(List<Map<String, List<TalkScheduleEntryDTO>>> tracks) {
}
