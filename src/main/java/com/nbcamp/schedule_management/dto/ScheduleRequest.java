package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    private String toDo;
    private String password;
    private String createdBy;
    private Long managerId;

    public Schedule toEntity() {
        return Schedule.of(null, toDo, createdBy, null, null, password, managerId);
    }
}
