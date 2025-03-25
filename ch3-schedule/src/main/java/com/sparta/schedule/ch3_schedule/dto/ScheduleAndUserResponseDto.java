package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleAndUserResponseDto {

    private Long schedule_id;
    private Long user_id;
    private String todo;
    private String author;
    private String email;
    private LocalDate create_date;
    private LocalDate update_date;

    public ScheduleAndUserResponseDto(Long schedule_id, Long user_id, String todo, String author, String email, LocalDateTime create_date, LocalDateTime update_date) {
        this.schedule_id = schedule_id;
        this.user_id = user_id;
        this.todo = todo;
        this.author = author;
        this.email = email;
        this.create_date = create_date.toLocalDate();
        this.update_date = update_date.toLocalDate();
    }

    public ScheduleAndUserResponseDto(Schedule schedule, User user) {
        this.schedule_id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = user.getAuthor();
        this.email = user.getEmail();
        this.create_date = schedule.getCreate_date().toLocalDate();
        this.update_date = schedule.getUpdate_date().toLocalDate();
    }

}
