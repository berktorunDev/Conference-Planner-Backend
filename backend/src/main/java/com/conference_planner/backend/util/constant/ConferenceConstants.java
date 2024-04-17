package com.conference_planner.backend.util.constant;

import java.time.LocalTime;

/**
 * Central class for managing constants used across the application.
 */
public class ConferenceConstants {
    public static final LocalTime MORNING_SESSION_START = LocalTime.of(9, 0);
    public static final LocalTime MORNING_SESSION_END = LocalTime.of(12, 0);
    public static final LocalTime LUNCH_TIME = LocalTime.of(12, 0);
    public static final LocalTime AFTERNOON_SESSION_START = LocalTime.of(13, 0);
    public static final LocalTime AFTERNOON_SESSION_END = LocalTime.of(17, 0);
    public static final LocalTime NETWORKING_EVENT_EARLIEST_START = LocalTime.of(16, 0);
    public static final LocalTime NETWORKING_EVENT_LATEST_START = LocalTime.of(17, 0);

    private ConferenceConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}