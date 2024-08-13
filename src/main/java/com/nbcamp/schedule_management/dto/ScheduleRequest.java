package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    private String toDo;
    private String password;
    private Long managerId;

    public Schedule toEntity() {
        return Schedule.of(null, toDo, null, null, password, managerId);
    }
}
