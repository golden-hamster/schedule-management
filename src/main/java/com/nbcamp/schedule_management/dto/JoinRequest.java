package com.nbcamp.schedule_management.dto;

import com.nbcamp.schedule_management.entity.Manager;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JoinRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    public Manager toEntity() {
        return Manager.of(null, name, email, null, null);
    }
}
