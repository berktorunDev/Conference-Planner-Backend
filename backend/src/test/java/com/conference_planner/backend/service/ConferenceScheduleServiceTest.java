package com.conference_planner.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.conference_planner.backend.model.dto.common.TalkDTO;
import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;
import com.conference_planner.backend.model.dto.request.ConferenceScheduleRequestDTO;
import com.conference_planner.backend.model.dto.response.ConferenceResponseDTO;

class ConferenceScheduleServiceTest {

    private ConferenceScheduleService service;

    @BeforeEach
    void setUp() {
        // Arrange
        service = new ConferenceScheduleService();
    }

    /**
     * Tests whether talks are scheduled correctly by the ConferenceScheduleService.
     */
    @Test
    void shouldScheduleTalksCorrectly() {
        // Arrange
        List<TalkDTO> talks = List.of(
                new TalkDTO("Architecting Your Codebase", "60min"),
                new TalkDTO("Cloud Native Java", "lightning"),
                new TalkDTO("Perfect Scalability", "30min"));
        ConferenceScheduleRequestDTO request = new ConferenceScheduleRequestDTO(talks);

        // Act
        ConferenceResponseDTO response = service.scheduleConference(request);

        // Assert
        assertFalse(response.tracks().isEmpty(), "There should be at least one track");
        assertTrue(response.tracks().get(0).containsKey("track_1"), "Track 1 should exist");

        // Further checks on the entries to ensure correct scheduling
        Map<String, List<TalkScheduleEntryDTO>> firstTrack = response.tracks().get(0);
        assertEquals(5, firstTrack.get("track_1").size(),
                "There should be five entries including lunch and networking");
        assertEquals("Lunch", firstTrack.get("track_1").get(3).title(), "Lunch should be scheduled correctly");
    }

    /**
     * Tests scheduling talks when no talks are provided in the request.
     * The service should handle this case gracefully.
     */
    @Test
    void shouldHandleEmptyRequest() {
        // Arrange
        ConferenceScheduleRequestDTO emptyRequest = new ConferenceScheduleRequestDTO(List.of());

        // Act
        ConferenceResponseDTO response = service.scheduleConference(emptyRequest);

        // Assert
        assertTrue(response.tracks().isEmpty(), "No tracks should be generated for an empty request");
    }

    /**
     * Tests scheduling talks when all talks are lightning talks.
     * The service should handle this scenario and schedule the talks accordingly.
     */
    @Test
    void shouldHandleAllLightningTalks() {
        // Arrange
        List<TalkDTO> lightningTalks = List.of(
                new TalkDTO("Lightning Talk 1", "lightning"),
                new TalkDTO("Lightning Talk 2", "lightning"));
        ConferenceScheduleRequestDTO request = new ConferenceScheduleRequestDTO(lightningTalks);

        // Act
        ConferenceResponseDTO response = service.scheduleConference(request);

        // Assert
        assertFalse(response.tracks().isEmpty(), "There should be at least one track");
        assertTrue(response.tracks().get(0).containsKey("track_1"), "Track 1 should exist");
        assertEquals(4, response.tracks().get(0).get("track_1").size(),
                "There should be three entries including lunch and networking for lightning talks");
    }
}
