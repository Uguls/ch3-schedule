package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private Long user_id;
    private String todo;
    private LocalDate create_date;
    private LocalDate update_date;

    public ScheduleResponseDto(Long id, Long user_id, String todo, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.user_id = user_id;
        this.todo = todo;
        this.create_date = create_date.toLocalDate();
        this.update_date = update_date.toLocalDate();
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.user_id = schedule.getUserId();
        this.create_date = schedule.getCreate_date().toLocalDate();
        this.update_date = schedule.getUpdate_date().toLocalDate();
    }
    
    public ScheduleResponseDto(ScheduleAndUserResponseDto scheduleAndUserResponseDto) {
        this.id = scheduleAndUserResponseDto.getSchedule_id();
        this.todo = scheduleAndUserResponseDto.getTodo();
        this.user_id = scheduleAndUserResponseDto.getUser_id();
        this.create_date = scheduleAndUserResponseDto.getCreate_date();
        this.update_date = scheduleAndUserResponseDto.getUpdate_date();
    }
}
