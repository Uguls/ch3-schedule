package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String todo;
    private String author;
    private Long userId;
    private String email;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public ScheduleResponseDto(Long id, String todo, Long userId, LocalDateTime create_time) {
        this.id = id;
        this.todo = todo;
        this.userId = userId;
        this.create_time = create_time;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.userId = schedule.getUserId();
        this.email = schedule.getEmail();
        this.create_time = schedule.getCreate_date();
        this.update_time = schedule.getUpdate_date();
    }

    public ScheduleResponseDto(Long id, String todo, Long userId, LocalDateTime create_time, LocalDateTime update_time) {
        this.id = id;
        this.todo = todo;
        this.userId = userId;
        this.create_time = create_time;
        this.update_time = update_time;
    }
}
