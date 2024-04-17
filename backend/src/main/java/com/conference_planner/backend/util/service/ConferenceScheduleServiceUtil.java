package com.conference_planner.backend.util.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.conference_planner.backend.model.dto.common.TalkDTO;
import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;
import com.conference_planner.backend.util.constant.ConferenceConstants;

public class ConferenceScheduleServiceUtil {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");

    /**
     * Private constructor to prevent instantiation.
     * This utility class is designed to be used statically and does not require
     * object instantiation.
     */
    private ConferenceScheduleServiceUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Formats the given LocalTime object into a human-readable string format,
     * using the predefined TIME_FORMATTER for consistency across all time displays.
     * 
     * @param time The time to format.
     * @return A formatted string representing the specified time.
     */
    public static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    /**
     * Schedules a series of talks within a specific time frame (morning or
     * afternoon session),
     * ensuring no overlap and that each talk fits within the session limits.
     * This method is designed to optimize session planning by filling up the
     * session time efficiently.
     * 
     * @param unscheduledTalks The list of talks that need to be scheduled.
     * @param entries          List of scheduled entries for the track, updated with
     *                         each scheduled talk.
     * @param startTime        Start time of the session.
     * @param endTime          End time of the session, usually marked by lunch or
     *                         end of the day.
     * @return The time after the last scheduled talk, useful for subsequent
     *         scheduling activities.
     */
    public static LocalTime scheduleSession(List<TalkDTO> unscheduledTalks, List<TalkScheduleEntryDTO> entries,
            LocalTime startTime, LocalTime endTime) {
        LocalTime currentTime = startTime;
        for (TalkDTO talk : new ArrayList<>(unscheduledTalks)) {
            LocalTime potentialEndTime = currentTime.plusMinutes(talk.getDurationInMinutes());
            if (potentialEndTime.isAfter(endTime)) {
                break; // Stop scheduling if the talk extends beyond the session limit
            }
            entries.add(new TalkScheduleEntryDTO(formatTime(currentTime), talk.title() + " " + talk.duration()));
            currentTime = potentialEndTime;
            unscheduledTalks.remove(talk);
        }
        return currentTime;
    }

    /**
     * Adds fixed time events such as lunch and networking to the track schedule.
     * These events are scheduled after the morning and afternoon sessions
     * respectively,
     * and are crucial for providing structured breaks and networking opportunities
     * at the conference.
     * 
     * @param currentTrack The current track being scheduled.
     * @param currentTime  The current time at which the last session ended or is
     *                     planned to end.
     */
    public static void addFixedEvents(List<TalkScheduleEntryDTO> currentTrack, LocalTime currentTime) {
        currentTrack.add(new TalkScheduleEntryDTO(formatTime(currentTime), "Lunch"));
        LocalTime networkingStartTime = currentTime.plusHours(1); // Assume 1 hour lunch
        if (networkingStartTime.isBefore(ConferenceConstants.NETWORKING_EVENT_EARLIEST_START)) {
            networkingStartTime = ConferenceConstants.NETWORKING_EVENT_EARLIEST_START;
        }
        currentTrack.add(new TalkScheduleEntryDTO(formatTime(networkingStartTime), "Networking Event"));
    }
}