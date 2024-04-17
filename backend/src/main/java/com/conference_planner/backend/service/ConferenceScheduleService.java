package com.conference_planner.backend.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.conference_planner.backend.model.dto.common.TalkDTO;
import com.conference_planner.backend.model.dto.common.TalkScheduleEntryDTO;
import com.conference_planner.backend.model.dto.request.ConferenceScheduleRequestDTO;
import com.conference_planner.backend.model.dto.response.ConferenceResponseDTO;
import com.conference_planner.backend.util.constant.ConferenceConstants;
import com.conference_planner.backend.util.service.ConferenceScheduleServiceUtil;

@Service
public class ConferenceScheduleService {
    /**
     * Plans an entire conference based on a list of talks provided in the request.
     * This method organizes talks into multiple tracks according to the conference
     * day structure,
     * ensuring that each track fits within a single day by segmenting talks into
     * morning and afternoon sessions,
     * incorporating fixed time events such as lunch and networking opportunities.
     *
     * @param scheduleRequest Contains details of all talks to be scheduled
     *                        including title and duration.
     * @return A ConferenceResponseDTO that details all the tracks organized,
     *         facilitating easy viewing and further processing.
     */
    public ConferenceResponseDTO scheduleConference(ConferenceScheduleRequestDTO scheduleRequest) {
        List<TalkDTO> unscheduledTalks = new ArrayList<>(scheduleRequest.talks());
        List<Map<String, List<TalkScheduleEntryDTO>>> allTracks = new ArrayList<>();
        int trackNumber = 1;

        // Process each talk until all are scheduled into tracks
        while (!unscheduledTalks.isEmpty()) {
            Map<String, List<TalkScheduleEntryDTO>> trackMap = new HashMap<>();
            List<TalkScheduleEntryDTO> currentTrack = new ArrayList<>();
            LocalTime currentTime = ConferenceConstants.MORNING_SESSION_START;

            // Morning session: Schedule until lunch
            currentTime = ConferenceScheduleServiceUtil.scheduleSession(unscheduledTalks, currentTrack, currentTime,
                    ConferenceConstants.LUNCH_TIME);
            currentTrack.add(new TalkScheduleEntryDTO(ConferenceScheduleServiceUtil.formatTime(currentTime), "Lunch"));

            // Afternoon session: Start after lunch and schedule until end of the day
            currentTime = ConferenceConstants.AFTERNOON_SESSION_START;
            currentTime = ConferenceScheduleServiceUtil.scheduleSession(unscheduledTalks, currentTrack, currentTime,
                    ConferenceConstants.AFTERNOON_SESSION_END);

            // Schedule a networking event if there's time at the end of the day
            LocalTime networkingStartTime = currentTime.isBefore(ConferenceConstants.NETWORKING_EVENT_EARLIEST_START)
                    ? ConferenceConstants.NETWORKING_EVENT_EARLIEST_START
                    : currentTime;
            currentTrack.add(new TalkScheduleEntryDTO(ConferenceScheduleServiceUtil.formatTime(networkingStartTime),
                    "Networking Event"));

            // Store completed track and prepare for a new one if necessary
            trackMap.put("track_" + trackNumber, currentTrack);
            allTracks.add(trackMap);
            trackNumber++;
        }

        return new ConferenceResponseDTO(allTracks);
    }
}
