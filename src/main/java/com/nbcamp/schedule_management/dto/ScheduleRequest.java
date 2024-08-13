package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Schedule;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequest {

    @Size(max = 200)
    @NotBlank
    private String toDo;

    @NotBlank
    private String password;

    @NotNull
    private Long managerId;

    public Schedule toEntity() {
        return Schedule.of(null, toDo, null, null, password, managerId);
    }
}
