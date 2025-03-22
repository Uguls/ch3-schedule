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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public User(Long id, String author, String email, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public User(String author, String email) {
        this.author = author;
        this.email = email;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
}
