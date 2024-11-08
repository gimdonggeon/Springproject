package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    // JdbcTemplate 주입
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 생성
    public Schedule createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedules (name, pw, contents, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getName(), schedule.getPw(), schedule.getContents(),
                schedule.getCreatedAt(), schedule.getUpdatedAt());

        // ID를 자동 생성된 값으로 반환
        String sqlForId = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(sqlForId, Long.class);
        schedule.setId(id);
        return schedule;
    }

    // 모든 일정 조회
    public List<Schedule> findAllSchedules() {
        String sql = "SELECT * FROM schedules";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("pw"),
                rs.getString("contents"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        ));
    }

    // ID로 일정 조회
    public Optional<Schedule> findScheduleById(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        List<Schedule> schedules = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("pw"),
                rs.getString("contents"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        ));
        return schedules.stream().findFirst();
    }

    // 일정 업데이트
    public void updateSchedule(Schedule schedule) {
        String sql = "UPDATE schedules SET name = ?, pw = ?, contents = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, schedule.getName(), schedule.getPw(), schedule.getContents(),
                schedule.getUpdatedAt(), schedule.getId());
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
