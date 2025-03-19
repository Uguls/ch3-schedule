package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;


@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ScheduleResponseDto save(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("author", schedule.getAuthor());
        parameters.put("password", schedule.getPassword());
        parameters.put("create_time", Timestamp.valueOf(schedule.getCreate_time()));
        parameters.put("update_time", Timestamp.valueOf(schedule.getUpdate_time()));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(), schedule.getAuthor(), schedule.getCreate_time());
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);

        return result.stream().findAny();
    }

    @Override
    public List<Schedule> findAll() {
        return List.of();
    }

    @Override
    public int deleteById(Long id, String password) {
        return 0;
    }

    @Override
    public int updateById(Long id, String password) {
        return 0;
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getTimestamp("create_time").toLocalDateTime(),
                        rs.getTimestamp("update_time").toLocalDateTime()
                );
            }
        };
    }
}
