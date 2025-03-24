package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    @Override
    public List<ScheduleResponseDto> findAll() {
        List<ScheduleResponseDto> all = scheduleRepository.findAll();
        return all;
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(String todo, Long id, String password) {

        if (todo == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The todo and password are required values.");
        }

        if (!passwordValid(id, password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password not correct.");
        }

        int updatedById = scheduleRepository.updateById(id, password, todo);
        if (updatedById == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new ScheduleResponseDto(scheduleRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id, String password) {
        if (password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is required values.");
        }

        if (!passwordValid(id, password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password not correct.");
        }

        int deleteById = scheduleRepository.deleteById(id, password);
        if (deleteById == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }
    }

    private Boolean passwordValid(Long id,String password) {
        String passwordById = scheduleRepository.findPasswordById(id);
        if (password.equals(passwordById)) {
            return false;
        } else {
            return true;
        }
    }

}
