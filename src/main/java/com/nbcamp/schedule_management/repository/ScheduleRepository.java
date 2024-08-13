package com.nbcamp.schedule_management.repository;

import com.nbcamp.schedule_management.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.PreparedStatement.*;

@RequiredArgsConstructor
@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Schedule> save(Schedule schedule) {
        String sql = "INSERT INTO schedule (to_do, created_at, modified_at, password, manager_id) VALUES (?, ?, ?, ?, ?)";

        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setModifiedAt(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement pstmt = con.prepareStatement(sql, RETURN_GENERATED_KEYS);

                pstmt.setString(1, schedule.getToDo());
                pstmt.setObject(2, Timestamp.valueOf(schedule.getCreatedAt()));
                pstmt.setObject(3, Timestamp.valueOf(schedule.getModifiedAt()));
                pstmt.setString(4, schedule.getPassword());
                pstmt.setLong(5, schedule.getManagerId());
                return pstmt;
            }, keyHolder);

            Number key = keyHolder.getKey();

            if (key == null) {
                return Optional.empty();
            }

            schedule.setId(key.longValue());
            return Optional.of(schedule);

    }

    public Optional<Schedule> findById(Long scheduleId) {
        String sql = "SELECT id, to_do, password, created_at, modified_at, manager_id FROM schedule WHERE id = ?";
        try {
            Schedule schedule = jdbcTemplate.queryForObject(sql, new RowMapper<Schedule>() {
                @Override
                public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long scheduleId = rs.getLong("id");
                    String toDo = rs.getString("to_do");
                    String password = rs.getString("password");
                    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime modifiedAt = rs.getTimestamp("modified_at").toLocalDateTime();
                    Long managerId = rs.getLong("manager_id");

                    return Schedule.of(scheduleId, toDo, createdAt, modifiedAt, password, managerId);
                }
            }, scheduleId);

            return Optional.ofNullable(schedule);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    public Page<Schedule> findSchedules(LocalDate modifiedAt, String managerName, Pageable pageable) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT s.* FROM schedule s " +
                        "JOIN manager m ON s.manager_id = m.id " +
                        "WHERE 1=1 "
        );

        if (modifiedAt != null) {
            sql.append("AND modified_at >= ? AND modified_at < ? ");
            params.add(modifiedAt.atStartOfDay());
            params.add(modifiedAt.plusDays(1).atStartOfDay());
        }

        if (managerName != null && !managerName.isEmpty()) {
            sql.append("AND m.name = ? ");
            params.add(managerName);
        }

        String countSql = "SELECT COUNT(*) FROM (" + sql + ") AS count_query";
        Integer totalItems = jdbcTemplate.queryForObject(countSql, Integer.class, params.toArray());

        sql.append("ORDER BY s.modified_at DESC LIMIT ? OFFSET ?");
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());

        List<Schedule> schedules = jdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Schedule.of(rs.getLong("id"),
                        rs.getString("to_do"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime(),
                        rs.getString("password"),
                        rs.getLong("manager_id"));

            }
        });

        return new PageImpl<>(schedules, pageable, totalItems != null ? totalItems : 0);
    }

    public Optional<Schedule> update(Schedule schedule) {
        String sql = "UPDATE schedule SET to_do = ?, manager_id = ?, modified_at = ? WHERE id = ?";

        schedule.setModifiedAt(LocalDateTime.now());

        int updatedRows = jdbcTemplate.update(sql,
                schedule.getToDo(),
                schedule.getManagerId(),
                Timestamp.valueOf(schedule.getModifiedAt()),
                schedule.getId());

        if (updatedRows == 0) {
            return Optional.empty();
        }

        return Optional.of(schedule);
    }

    public void delete(Schedule schedule) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, schedule.getId());
    }
}
