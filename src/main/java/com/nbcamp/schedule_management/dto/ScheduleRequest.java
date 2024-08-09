package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    private String todo;
    private String password;
    private String createdBy;
    private Long managerId;

    public Schedule toEntity() {
        return Schedule.of(todo, createdBy, password, managerId);
    }
}
