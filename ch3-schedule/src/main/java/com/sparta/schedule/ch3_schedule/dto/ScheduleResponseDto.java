package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String todo;
    private String author;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public ScheduleResponseDto(Long id, String todo, String author, LocalDateTime create_time) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.create_time = create_time;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.create_time = schedule.getCreate_time();
        this.update_time = schedule.getUpdate_time();
    }
}
