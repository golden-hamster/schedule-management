package com.nbcamp.schedule_management.repository;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.sql.PreparedStatement.*;

@RequiredArgsConstructor
@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Schedule> save(Schedule schedule) {
        String sql = "INSERT INTO schedule (to_do, created_by, created_at, modified_at, password, manager_id) VALUES (?, ?, ?, ?, ?, ?)";

        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setModifiedAt(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement pstmt = con.prepareStatement(sql, RETURN_GENERATED_KEYS);

                pstmt.setString(1, schedule.getToDo());
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
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Schedule> findById(Long scheduleId) {
        String sql = "SELECT id, to_do, password, created_at, modified_at, manager_id, created_by FROM schedule WHERE id = ?";

        try {
            Schedule schedule = jdbcTemplate.queryForObject(sql, new RowMapper<Schedule>() {
                @Override
                public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long scheduleId = rs.getLong("id");
                    String toDo = rs.getString("to_do");
                    String password = rs.getString("password");
                    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime modifiedAt = rs.getTimestamp("modified_at").toLocalDateTime();
                    String createdBy = rs.getString("created_by");
                    Long managerId = rs.getLong("manager_id");

                    return Schedule.of(scheduleId, toDo, createdBy, createdAt, modifiedAt, password, managerId);
                }
            }, scheduleId);

            return Optional.ofNullable(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
