package com.nbcamp.schedule_management.controller;

import com.nbcamp.schedule_management.dto.ScheduleListResponse;
import com.nbcamp.schedule_management.dto.ScheduleRequest;
import com.nbcamp.schedule_management.dto.ScheduleResponse;
import com.nbcamp.schedule_management.dto.ScheduleUpdateRequest;
import com.nbcamp.schedule_management.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponse> saveSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.saveSchedule(scheduleRequest);
        return ResponseEntity.ok(scheduleResponse);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> findSchedule(@PathVariable Long scheduleId) {
        ScheduleResponse scheduleResponse = scheduleService.findById(scheduleId);
        return ResponseEntity.ok(scheduleResponse);
    }

    @GetMapping
    public ResponseEntity<ScheduleListResponse> findSchedules(
            @RequestParam(required = false) String modifiedDate,
            @RequestParam(required = false) String managerName,
            @PageableDefault(page = 0, size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        ScheduleListResponse scheduleListResponse = ScheduleListResponse.of(scheduleService.findSchedules(modifiedDate, managerName, pageable));
        return ResponseEntity.ok(scheduleListResponse);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest);
        return ResponseEntity.ok(scheduleResponse);
    }
}
