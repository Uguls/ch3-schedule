package com.sparta.schedule.ch3_schedule.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String author;
    private String password;
    private Date create_time;
    private Date update_time;
}
