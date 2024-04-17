package com.conference_planner.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;

class TalkScheduleEntryDTOTest {

    @Test
    @DisplayName("TalkScheduleEntryDTO should correctly store and return time and title")
    void testTalkScheduleEntryCreation() {
        TalkScheduleEntryDTO entry = new TalkScheduleEntryDTO("09:00 AM", "Sample Talk");
        assertEquals("09:00 AM", entry.time(), "Stored time should match the provided time");
        assertEquals("Sample Talk", entry.title(), "Stored title should match the provided title");
    }
}
