package com.sparta.schedule.ch3_schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("존재하지 않는 일정입니다. id = " + id);
    }
}