package com.nbcamp.schedule_management.service;

import com.nbcamp.schedule_management.dto.ScheduleRequest;
import com.nbcamp.schedule_management.dto.ScheduleResponse;
import com.nbcamp.schedule_management.entity.Schedule;
import com.nbcamp.schedule_management.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.save(scheduleRequest.toEntity()).orElseThrow(RuntimeException::new);
        return ScheduleResponse.from(schedule);
    }

    public ScheduleResponse findById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        return ScheduleResponse.from(schedule);
    }
}
