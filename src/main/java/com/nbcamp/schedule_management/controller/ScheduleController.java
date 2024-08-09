package com.nbcamp.schedule_management.controller;

import com.nbcamp.schedule_management.dto.ScheduleRequest;
import com.nbcamp.schedule_management.dto.ScheduleResponse;
import com.nbcamp.schedule_management.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> saveSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.saveSchedule(scheduleRequest);
        return ResponseEntity.ok(scheduleResponse);
    }
}
