package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto save(Schedule schedule); // 저장

    Optional<Schedule> findById(Long id); // 단일 조회

    List<Schedule> findAll(); // 전체 조회

    int deleteById(Long id, String password); // 삭제

    int updateById(Long id, String password); // 수정
}
