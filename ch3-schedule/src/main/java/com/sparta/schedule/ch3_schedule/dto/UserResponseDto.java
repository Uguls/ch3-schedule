package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private Long id;
    private String author;
    private String email;
    private LocalDate create_date;
    private LocalDate update_date;

    public UserResponseDto(Long id, String author, String email, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.create_date = create_date.toLocalDate();
        this.update_date = update_date.toLocalDate();
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.author = user.getAuthor();
        this.email = user.getEmail();
        this.create_date = user.getCreateDate().toLocalDate();
        this.update_date = user.getUpdateDate().toLocalDate();
    }
}
