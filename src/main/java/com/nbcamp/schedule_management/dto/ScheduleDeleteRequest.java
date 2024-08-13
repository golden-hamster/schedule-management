package com.nbcamp.schedule_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequest {

    @NotBlank
    private String password;
}
