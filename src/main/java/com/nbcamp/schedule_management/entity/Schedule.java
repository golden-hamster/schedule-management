package com.nbcamp.schedule_management.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Schedule {

    @Setter
    private Long id;

    private String todo;

    private String createdBy;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime modifiedAt;

    private String password;

    private Long ManagerId;

    private Schedule(String todo, String createdBy, String password, Long managerId) {
        this.todo = todo;
        this.createdBy = createdBy;
        this.password = password;
        this.ManagerId = managerId;
    }

    public static Schedule of(String todo, String createdBy, String password, Long managerId) {
        return new Schedule(todo, createdBy, password, managerId);
    }
}
