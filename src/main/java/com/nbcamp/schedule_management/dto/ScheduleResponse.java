package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ScheduleResponse {
    private Long id;
    private String todo;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(schedule.getId(), schedule.getTodo(), schedule.getCreatedBy(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
