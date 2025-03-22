package com.sparta.schedule.ch3_schedule.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String password;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private Long userId;  // FK

    public Schedule(String todo, String password) {
        this.todo = todo;
        this.password = password;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

    public void setTodo(String todo) {
        this.todo = todo;
        this.update_date = LocalDateTime.now();
    }

}
