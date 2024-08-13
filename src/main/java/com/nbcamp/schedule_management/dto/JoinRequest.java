package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Manager;
import lombok.Getter;

@Getter
public class JoinRequest {
    private String name;
    private String email;

    public Manager toEntity() {
        return Manager.of(null, name, email, null, null);
    }
}
