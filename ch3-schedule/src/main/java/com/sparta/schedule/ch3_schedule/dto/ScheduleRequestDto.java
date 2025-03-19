package com.sparta.schedule.ch3_schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private Long id;

    private String todo;

    private String author;

    private String password;

}
