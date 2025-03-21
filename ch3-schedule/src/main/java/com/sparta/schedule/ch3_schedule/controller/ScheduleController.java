package com.sparta.schedule.ch3_schedule.controller;

import com.sparta.schedule.ch3_schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.ch3_schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.ch3_schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * @param dto 일정 정보를 담은 요청 객체 (todo, author, password)
     * @return 생성된 일정 정보를 포함한 ResponseEntity 객체 (201 Created)
     */
    @PostMapping("/")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.addSchedule(dto.getTodo(), dto.getAuthor(), dto.getPassword()), HttpStatus.CREATED);
    }

    /**
     * @return 모든 게시글을 List형태로 반환
     */
    @GetMapping("/")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> all = scheduleService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * @param id 조회할 일정의 ID
     * @return 해당 ID의 일정 정보를 담은 객체를 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto scheduleById = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(scheduleById);
    }

    /**
     * @param id 수정할 일정의 id
     * @param dto 수정할 내용 및 비밀번호
     * @return 변경된 할일 반환
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(dto.getTodo(), id, dto.getPassword());
        return ResponseEntity.ok(scheduleResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        try {
            scheduleService.deleteSchedule(id, dto.getPassword());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }


}
