package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto addSchedule(String todo, String author, String password);

    ScheduleResponseDto findScheduleById(Long id);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto updateSchedule(String todo, Long id, String password);

}
