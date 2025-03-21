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
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public ScheduleResponseDto(Long id, String todo, Long userId, LocalDateTime create_date) {
        this.id = id;
        this.todo = todo;
        this.userId = userId;
        this.create_date = create_date;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.userId = schedule.getUserId();
        this.email = schedule.getEmail();
        this.create_date = schedule.getCreate_date();
        this.update_date = schedule.getUpdate_date();
    }

    public ScheduleResponseDto(Long id, String todo, Long userId, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.todo = todo;
        this.userId = userId;
        this.create_date = create_date;
        this.update_date = update_date;
    }
}
