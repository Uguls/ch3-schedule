package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public Schedule save(Schedule schedule, User user) {
        // TODO
        // - user 테이블에서 email, author로 조회 후 있으면 id로 schedule에 저장
        // - 기존 유저가 아닌경우 새로 만들기
        Long userId;
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());


        if (byEmail.isPresent()) {
            userId = byEmail.get().getId();
        } else {
            // TODO 새로운 유저 저장
            SimpleJdbcInsert jdbcInsertUser = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsertUser.withTableName("user").usingGeneratedKeyColumns("id");

            Map<String, Object> userParameters = new HashMap<>();
            userParameters.put("author", user.getAuthor());
            userParameters.put("email", user.getEmail());
            userParameters.put("create_date", LocalDateTime.now());
            userParameters.put("update_date", LocalDateTime.now());

            Number userKey = jdbcInsertUser.executeAndReturnKey(new MapSqlParameterSource(userParameters));
            userId = userKey.longValue();
        }

        // TODO schedule 테이블에 데이터 저장
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("password", schedule.getPassword());
        parameters.put("create_date", LocalDateTime.now());
        parameters.put("update_date", LocalDateTime.now());
        parameters.put("user_id", userId);

        // 자동생성된 값 즉 id
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Schedule(
                key.longValue(),
                schedule.getTodo(),
                user.getAuthor(),
                userId,
                user.getEmail(),
                schedule.getCreate_date(),
                schedule.getUpdate_date()
        );
    }

    /**
     * @param id 조회할 일정의 id
     * @return id를 사용하여 조회한 일정
     */
    @Override
    public Optional<Schedule> findById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);

        return result.stream().findAny();
    }

    /**
     * @return 데이터베이스에 저장된 모든 일정을 리스트형태로 반환
     */
    @Override
    public List<Schedule> findAll() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    /**
     * @param id 삭제할 일정의 id
     * @param password 삭제할 일정의 password
     * @return
     */
    @Override
    public int deleteById(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where id = ? AND password = ?", id, password);
    }

    /**
     * @param id 수정할 일정의 id
     * @param password 수정할 일정의 password
     * @param todo 수정할 일정의 내용
     * @return 수정된 일정의 정보
     */
    @Override
    public int updateById(Long id, String password, String todo) {
        return jdbcTemplate.update("update schedule set todo = ? where id = ? AND password = ? AND update_time = NOW()", todo, id, password);
    }

    /**
     * @param id id를 기반으로 password찾기
     * @return 해당 컬럼이 존재할 경우 password 반환, 없을 경우 ""반환
     */
    @Override
    public String findPasswordById(Long id) {
        try {
            String query = jdbcTemplate.queryForObject("select password from schedule where id = ?", String.class, id);
            return query;
        } catch (EmptyResultDataAccessException e) {
            return "";
        }
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("password"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime(),
                        rs.getLong("user_id")
                );
            }
        };
    }
}
