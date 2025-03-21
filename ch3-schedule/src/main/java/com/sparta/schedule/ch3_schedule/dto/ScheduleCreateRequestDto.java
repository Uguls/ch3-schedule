package com.sparta.schedule.ch3_schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ScheduleCreateRequestDto {
    private String todo;
    private String author;
    private String password;
    private String email;
}
