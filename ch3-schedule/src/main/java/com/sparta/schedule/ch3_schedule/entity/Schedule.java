package com.sparta.schedule.ch3_schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String author;
    private String password;
    private Date create_time;
    private Date update_time;

}
