package com.sparta.schedule.ch3_schedule.service;

import com.sparta.schedule.ch3_schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.entity.Schedule;
import com.sparta.schedule.ch3_schedule.entity.User;
import com.sparta.schedule.ch3_schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto dto) {
        User user = new User(dto.getAuthor(), dto.getEmail());
        Schedule schedule = new Schedule(dto.getTodo(), user.getAuthor(), dto.getPassword(), user.getEmail());

        return new ScheduleResponseDto(scheduleRepository.save(schedule, user));
    }

    /**
     * @param id 조회할 일정의 id
     * @exception ResponseStatusException 데이터베이스에 존재하지 않는 id일 경우 발생
     * @return 조회한 일정 반환
     */
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<com.sparta.schedule.ch3_schedule.entity.Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "\"Does not exist id = \" + id");
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    /**
     * @return 모든 일정 반환
     */
    @Override
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule);
            responseDtoList.add(new ScheduleResponseDto(schedule));
        }

        return responseDtoList;
    }

    /**
     * @param todo 수정할 일정 내용
     * @param id 수정할 일정의 id
     * @param password 수정할 일정의 password
     * @exception ResponseStatusException password 또는 todo가 없을 경우, password가 일치하지 않을 경우, 오류로 인해 변경이 이루어지지 않을 경우 에러 발생
     * @return 수정된 일정 반환
     */
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

    /**
     * @param id 삭제할 일정의 id
     * @param password 삭제할 일정의 password
     */
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

    /**
     * @param id 비밀번호를 입력한 일정의 id
     * @param password 입력한 password
     * @return 데이터베이스에 저장된 password와 입력한 password가 일치할 경우 true, 불일치시 false
     */
    private Boolean passwordValid(Long id,String password) {
        String passwordById = scheduleRepository.findPasswordById(id);

        if (password != passwordById) {
            return false;
        } else {
            return true;
        }

    }

}
