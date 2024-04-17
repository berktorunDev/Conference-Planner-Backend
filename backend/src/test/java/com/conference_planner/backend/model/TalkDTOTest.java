package com.conference_planner.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.conference_planner.backend.model.dto.common.TalkDTO;

class TalkDTOTest {

    @Test
    @DisplayName("Regular duration should be correctly parsed from minutes")
    void testGetDurationInMinutes_Regular() {
        TalkDTO talk = new TalkDTO("Sample Talk", "45min");
        assertEquals(45, talk.getDurationInMinutes(), "Duration should be correctly parsed from '45min'");
    }

    @Test
    @DisplayName("Lightning talk should always return a duration of 5 minutes")
    void testGetDurationInMinutes_Lightning() {
        TalkDTO talk = new TalkDTO("Lightning Talk", "lightning");
        assertEquals(5, talk.getDurationInMinutes(), "Lightning talks should have a duration of 5 minutes");
    }

    @Test
    @DisplayName("Should throw NumberFormatException if duration is not properly formatted")
    void testGetDurationInMinutes_InvalidFormat() {
        assertThrows(NumberFormatException.class,
                () -> new TalkDTO("Improper Talk", "50 minutes").getDurationInMinutes(),
                "Expected NumberFormatException for improperly formatted duration string");
    }
}
