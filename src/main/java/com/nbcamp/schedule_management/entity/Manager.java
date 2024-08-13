package com.nbcamp.schedule_management.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Manager {

    @Setter
    private Long id;

    private String name;

    private String email;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime modifiedAt;

    public static Manager of(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new Manager(id, name, email, createdAt, modifiedAt);
    }
}
