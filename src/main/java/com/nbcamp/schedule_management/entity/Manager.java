package com.nbcamp.schedule_management.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Manager {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
