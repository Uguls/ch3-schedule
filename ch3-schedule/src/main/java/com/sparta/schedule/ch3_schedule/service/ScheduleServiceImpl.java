package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.*;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;
import com.sparta.schedule.ch3_schedule.exception.PasswordMismatchException;
import com.sparta.schedule.ch3_schedule.exception.ScheduleNotFoundException;
import com.sparta.schedule.ch3_schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * @param dto 등록할 일정, 유저 정보
     * @return 등록된 일정 정보
     */
    @Override
    public ScheduleAndUserResponseDto addSchedule(ScheduleCreateRequestDto dto) {
        User user = new User(dto.getAuthor(), dto.getEmail());
        Schedule schedule = new Schedule(dto.getTodo(), dto.getPassword());

        return scheduleRepository.save(schedule, user);
    }

    /**
     * @param id 조회할 일정의 id
     * @exception ResponseStatusException 데이터베이스에 존재하지 않는 id일 경우 발생
     * @return 조회한 일정 반환
     */
    @Override
    public ScheduleAndUserResponseDto findScheduleById(Long id) {
        Optional<ScheduleAndUserResponseDto> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ScheduleNotFoundException(id);
        }

        return optionalSchedule.get();
    }

    /**
     * @return 모든 일정 반환
     */
    @Override
    public List<ScheduleAndUserResponseDto> findAll(int page, int size) {
        return scheduleRepository.findAll(page, size);
    }

    /**
     * @param id 수정할 일정의 id
     * @exception ResponseStatusException password 또는 todo가 없을 경우, password가 일치하지 않을 경우, 오류로 인해 변경이 이루어지지 않을 경우 에러 발생
     * @return 수정된 일정 반환
     */
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        if (dto.getPassword() == null) {
            throw new IllegalArgumentException("비밀번호는 필수 입력입니다.");
        }
        if (!passwordValid(id, dto.getPassword())) {
            throw new PasswordMismatchException();
        }

        int updatedById = scheduleRepository.updateById(id, dto.getPassword(), dto.getTodo());

        if (updatedById == 0) {
            throw new ScheduleNotFoundException(id);
        }

        return new ScheduleResponseDto(scheduleRepository.findById(id).get());
    }

    /**
     * @param id  삭제할 일정의 id
     * @param dto 삭제일 일정의 비밀번호
     */
    @Transactional
    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        if (dto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is required values.");
        }

        if (!passwordValid(id, dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password not correct.");
        }

        int deleteById = scheduleRepository.deleteById(id, dto.getPassword());
        if (deleteById == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }
    }

    /**
     * @param id 비밀번호를 입력한 일정의 id
     * @param password 입력한 password
     * @return 데이터베이스에 저장된 password와 입력한 password가 일치할 경우 true, 불일치시 false
     */
    private Boolean passwordValid(Long id,String password) {
        String passwordById = scheduleRepository.findPasswordById(id);

        if (!password.equals(passwordById)) {
            return false;
        } else {
            return true;
        }

    }

}
