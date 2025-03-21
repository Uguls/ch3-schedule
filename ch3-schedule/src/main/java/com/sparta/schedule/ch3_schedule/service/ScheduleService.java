package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleCreateRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleDeleteRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleUpdateRequestDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto addSchedule(ScheduleCreateRequestDto dto);

    ScheduleResponseDto findScheduleById(Long id);

    List<ScheduleResponseDto> findAll(int page, int size);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);

    void deleteSchedule(Long id, ScheduleDeleteRequestDto dto);
}
