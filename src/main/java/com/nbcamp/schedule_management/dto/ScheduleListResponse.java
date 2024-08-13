package com.nbcamp.schedule_management.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ScheduleListResponse {
    private Page<ScheduleResponse> scheduleResponses;

    public static ScheduleListResponse of(Page<ScheduleResponse> scheduleResponses) {
        return new ScheduleListResponse(scheduleResponses);
    }
}
