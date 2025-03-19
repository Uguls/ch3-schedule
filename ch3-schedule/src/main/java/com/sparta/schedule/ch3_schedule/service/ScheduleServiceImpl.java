package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto addSchedule(String todo, String author, String password) {
        Schedule schedule = new Schedule(todo, author, password);
        return scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "\"Does not exist id = \" + id");
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

}
