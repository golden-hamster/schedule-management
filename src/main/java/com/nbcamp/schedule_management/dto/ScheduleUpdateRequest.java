package com.nbcamp.schedule_management.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {
    private String toDo;
    private Long managerId;
    private String password;
}
