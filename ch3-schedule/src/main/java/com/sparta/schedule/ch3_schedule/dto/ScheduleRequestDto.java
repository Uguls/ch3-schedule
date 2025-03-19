package com.sparta.schedule.ch3_schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private String todo;

    private String author;

    private String password;

}
