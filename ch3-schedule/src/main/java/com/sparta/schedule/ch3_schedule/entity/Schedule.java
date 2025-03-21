package com.sparta.schedule.ch3_schedule.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String author;
    private String password;
    private String email;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private Long userId;  // FK

    public Schedule(Long id, String todo, String author, LocalDateTime create_date, LocalDateTime update_date, Long userId) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.create_date = create_date;
        this.update_date = update_date;
        this.userId = userId;
    }

    public Schedule(String todo, String author, String password, String email) {
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.email = email;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

    public Schedule(Long id, String todo, String author, Long userId, String email, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.userId = userId;
        this.email = email;
        this.create_date = create_date;
        this.update_date = update_date;
    }
}
