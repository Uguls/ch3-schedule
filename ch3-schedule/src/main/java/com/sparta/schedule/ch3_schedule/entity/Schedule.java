package com.sparta.schedule.ch3_schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String author;
    private String password;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public Schedule(Long id, String todo, String author, LocalDateTime create_time, LocalDateTime update_time) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Schedule(String todo, String author, String password) {
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.create_time = LocalDateTime.now();
        this.update_time = LocalDateTime.now();
    }
}
