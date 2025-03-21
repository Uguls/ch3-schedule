package com.sparta.schedule.ch3_schedule.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class User {

    private Long id;
    private String author;
    private String email;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public User(Long id, String author, String email, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

    public User(String author, String email) {
        this.author = author;
        this.email = email;
    }
}
