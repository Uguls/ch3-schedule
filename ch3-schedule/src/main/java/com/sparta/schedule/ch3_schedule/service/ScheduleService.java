package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto addSchedule(ScheduleRequestDto dto);

    ScheduleResponseDto findScheduleById(Long id);

    List<ScheduleResponseDto> findAll(int page, int size);

    ScheduleResponseDto updateSchedule(String todo, Long id, String password);

    void deleteSchedule(Long id, String password);
}
