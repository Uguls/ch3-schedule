package com.sparta.schedule.ch3_schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequestDto {
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
