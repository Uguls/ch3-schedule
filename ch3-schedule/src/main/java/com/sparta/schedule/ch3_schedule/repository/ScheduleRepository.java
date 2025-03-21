package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule, User user); // 저장
    Optional<Schedule> findById(Long id); // 단일 조회
    List<Schedule> findAll(); // 전체 조회
    int deleteById(Long id, String password); // 삭제
    int updateById(Long id, String password, String todo); // 수정
    String findPasswordById(Long id);
}
