package com.sparta.schedule.ch3_schedule.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    @NotBlank(message = "할일은 필수입니다.")
    @Size(max = 200, message = "할일은 200자 이내여야 합니다.")
    private String todo;

    @NotBlank(message = "작성자는 필수입니다.")
    private String author;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
