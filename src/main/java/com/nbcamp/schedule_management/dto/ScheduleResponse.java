package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Manager;
import com.nbcamp.schedule_management.entity.Schedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ScheduleResponse {
    private Long id;
    private String toDo;
    private String managerName;
    private String createdAt;
    private String modifiedAt;

    public static ScheduleResponse from(Schedule schedule, Manager manager) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getToDo(),
                manager.getName(),
                schedule.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                schedule.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))
        );
    }
}
