package com.nbcamp.schedule_management.repository;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Schedule> save(Schedule schedule) {
        String sql = "INSERT INTO schedule (todo, created_by, created_at, modified_at, password, manager_id) VALUES (?, ?, ?, ?, ?, ?)";

        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setModifiedAt(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, schedule.getTodo());
            pstmt.setString(2, schedule.getCreatedBy());
            pstmt.setObject(3, Timestamp.valueOf(schedule.getCreatedAt()));
            pstmt.setObject(4, Timestamp.valueOf(schedule.getModifiedAt()));
            pstmt.setString(5, schedule.getPassword());
            pstmt.setLong(6, schedule.getManagerId());
            return pstmt;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if (key == null) {
            return Optional.empty();
        }

        schedule.setId(key.longValue());
        return Optional.of(schedule);
    }
}
