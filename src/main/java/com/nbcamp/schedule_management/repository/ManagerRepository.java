package com.nbcamp.schedule_management.repository;

import com.nbcamp.schedule_management.entity.Manager;
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

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@RequiredArgsConstructor
@Repository
public class ManagerRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Manager> save(Manager manager) {
        String sql = "INSERT INTO manager (name, email, created_at, modified_at) VALUES (?, ?, ?, ?)";

        manager.setCreatedAt(LocalDateTime.now());
        manager.setModifiedAt(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement pstmt = con.prepareStatement(sql, RETURN_GENERATED_KEYS);

                pstmt.setString(1, manager.getName());
                pstmt.setString(2, manager.getEmail());
                pstmt.setObject(3, Timestamp.valueOf(manager.getCreatedAt()));
                pstmt.setObject(4, Timestamp.valueOf(manager.getModifiedAt()));
                return pstmt;
            }, keyHolder);

            Number key = keyHolder.getKey();

            if (key == null) {
                return Optional.empty();
            }

            manager.setId(key.longValue());
            return Optional.of(manager);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Manager> findById(Long managerId) {
        String sql = "SELECT id, name, email, created_at, modified_at FROM manager WHERE id = ?";

        try {
            Manager schedule = jdbcTemplate.queryForObject(sql, new RowMapper<Manager>() {
                @Override
                public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime modifiedAt = rs.getTimestamp("modified_at").toLocalDateTime();

                    return Manager.of(id, name, email, createdAt, modifiedAt);
                }
            }, managerId);

            return Optional.ofNullable(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
