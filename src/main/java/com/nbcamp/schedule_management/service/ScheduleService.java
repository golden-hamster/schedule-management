package com.nbcamp.schedule_management.service;

import com.nbcamp.schedule_management.dto.ScheduleListResponse;
import com.nbcamp.schedule_management.dto.ScheduleRequest;
import com.nbcamp.schedule_management.dto.ScheduleResponse;
import com.nbcamp.schedule_management.entity.Manager;
import com.nbcamp.schedule_management.entity.Schedule;
import com.nbcamp.schedule_management.repository.ManagerRepository;
import com.nbcamp.schedule_management.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Transactional
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.save(scheduleRequest.toEntity()).orElseThrow(RuntimeException::new);
        Manager manager = managerRepository.findById(schedule.getManagerId()).orElseThrow(RuntimeException::new);
        return ScheduleResponse.from(schedule, manager);
    }

    public ScheduleResponse findById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        Manager manager = managerRepository.findById(schedule.getManagerId()).orElseThrow(RuntimeException::new);
        return ScheduleResponse.from(schedule, manager);
    }

    public Page<ScheduleResponse> findSchedules(String modifiedDate, String managerName, Pageable pageable) {
        LocalDate modifiedAt = null;
        if (modifiedDate != null) {
            modifiedAt = LocalDate.parse(modifiedDate);
        }

        Page<Schedule> schedules = scheduleRepository.findSchedules(modifiedAt, managerName, pageable);
        return schedules.map(schedule -> ScheduleResponse.from(schedule, managerRepository.findById(schedule.getManagerId()).orElseThrow(RuntimeException::new)));
    }
}
