package com.nbcamp.schedule_management.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
public class Schedule {

    private Long id;

    private String toDo;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String password;

    private Long ManagerId;

    public static Schedule of(Long id, String toDo, LocalDateTime createdAt, LocalDateTime modifiedAt, String password, Long managerId) {
        return new Schedule(id, toDo, createdAt, modifiedAt, password, managerId);
    }

}
