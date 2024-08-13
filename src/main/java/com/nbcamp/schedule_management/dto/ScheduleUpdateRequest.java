package com.nbcamp.schedule_management.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    @Size(max = 200)
    @NotBlank
    private String toDo;

    private Long managerId;

    @NotBlank
    private String password;
}
