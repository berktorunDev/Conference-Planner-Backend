package com.conference_planner.backend.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.conference_planner.backend.model.dto.common.TalkDTO;
import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;
import com.conference_planner.backend.util.service.ConferenceScheduleServiceUtil;

class ConferenceScheduleServiceUtilTest {

    /**
     * Tests the formatting of time into AM/PM format.
     */
    @Test
    void testFormatTime() {
        // Arrange
        LocalTime time = LocalTime.of(14, 30);

        // Act & Assert
        assertEquals("2:30 PM", ConferenceScheduleServiceUtil.formatTime(time),
                "Time should be formatted in AM/PM format");
    }

    /**
     * Tests scheduling talks into a session with multiple talks.
     */
    @Test
    void testScheduleSession_WithMultipleSessions() {
        // Arrange
        List<TalkDTO> talks = new ArrayList<>();
        talks.add(new TalkDTO("Sample Talk 1", "60min"));
        talks.add(new TalkDTO("Sample Talk 2", "45min"));
        talks.add(new TalkDTO("Sample Talk 3", "30min"));

        List<TalkScheduleEntryDTO> entries = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(12, 0);

        // Act
        LocalTime result = ConferenceScheduleServiceUtil.scheduleSession(talks, entries, startTime, endTime);

        // Assert
        assertEquals(LocalTime.of(10, 15), result, "Session should end at 10:15 AM");
        assertEquals(3, entries.size(), "Three talks should be scheduled");
        assertEquals("9:00 AM Sample Talk 1 60min", entries.get(0).time() + " " + entries.get(0).title(),
                "First talk scheduled incorrectly");
    }

    /**
     * Tests scheduling talks into a session where there's no space for any talk.
     */
    @Test
    void testScheduleSession_WithNoSpaceForTalk() {
        // Arrange
        List<TalkDTO> talks = new ArrayList<>();
        talks.add(new TalkDTO("Sample Talk 1", "60min"));

        List<TalkScheduleEntryDTO> entries = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(9, 30); // No space for any talks

        // Act
        LocalTime result = ConferenceScheduleServiceUtil.scheduleSession(talks, entries, startTime, endTime);

        // Assert
        assertEquals(startTime, result, "Session should not progress");
        assertTrue(entries.isEmpty(), "No talks should be scheduled");
    }
}
