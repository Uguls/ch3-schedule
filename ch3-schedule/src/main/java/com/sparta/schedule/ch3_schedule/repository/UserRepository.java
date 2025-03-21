package com.sparta.schedule.ch3_schedule.repository;

import com.sparta.schedule.ch3_schedule.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
