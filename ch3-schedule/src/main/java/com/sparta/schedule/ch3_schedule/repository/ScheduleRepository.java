package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.dto.ScheduleAndUserResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleAndUserResponseDto save(Schedule schedule, User user); // 저장
    Optional<ScheduleAndUserResponseDto> findById(Long id); // 단일 조회
    List<ScheduleAndUserResponseDto> findAll(int page, int size); // 전체 조회
    int deleteById(Long id, String password); // 삭제
    int updateById(Long id, String password, String todo); // 수정
    String findPasswordById(Long id);
}
