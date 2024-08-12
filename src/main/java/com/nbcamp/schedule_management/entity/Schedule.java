package com.nbcamp.schedule_management.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Schedule {

    @Setter
    private Long id;

    private String toDo;

    private String createdBy;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime modifiedAt;

    private String password;

    private Long ManagerId;

    public static Schedule of(Long id, String toDo, String createdBy, LocalDateTime createdAt, LocalDateTime modifiedAt, String password, Long managerId) {
        return new Schedule(id, toDo, createdBy, createdAt, modifiedAt, password, managerId);
    }

}
