package com.nbcamp.schedule_management.controller;

import com.nbcamp.schedule_management.dto.JoinRequest;
import com.nbcamp.schedule_management.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/managers")
@RestController
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<Void> saveManager(@RequestBody JoinRequest joinRequest) {
        managerService.saveManager(joinRequest);

        return ResponseEntity.noContent().build();
    }
}
