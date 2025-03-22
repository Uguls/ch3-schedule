package com.sparta.schedule.ch3_schedule.dto;

import com.sparta.schedule.ch3_schedule.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private Long id;
    private String author;
    private String email;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public UserResponseDto(Long id, String author, String email, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.author = user.getAuthor();
        this.email = user.getEmail();
        this.create_date = user.getCreateDate();
        this.update_date = user.getUpdateDate();
    }
}
