package com.sparta.schedule.ch3_schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private String todo;
    private String password;
    private String author;
    private String email;

}
