package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleAndUserResponseDto addSchedule(ScheduleCreateRequestDto dto);

    ScheduleAndUserResponseDto findScheduleById(Long id);

    List<ScheduleAndUserResponseDto> findAll(int page, int size, LocalDate updateDate, String author);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);

    void deleteSchedule(Long id, ScheduleDeleteRequestDto dto);
}
