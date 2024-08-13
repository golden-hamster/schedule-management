package com.nbcamp.schedule_management.service;

import com.nbcamp.schedule_management.dto.JoinRequest;
import com.nbcamp.schedule_management.exception.ManagerNotFoundException;
import com.nbcamp.schedule_management.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Transactional
    public void saveManager(JoinRequest joinRequest) {
        managerRepository.save(joinRequest.toEntity()).orElseThrow(ManagerNotFoundException::new);
    }
}
