package com.sparta.schedule.ch3_schedule.controller;

import com.sparta.schedule.ch3_schedule.dto.*;
import com.sparta.schedule.ch3_schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * @param dto 일정 정보 및 작성자 정보를 담은 요청 객체 (todo, author, password, email)
     * @return 생성된 일정 정보를 포함한 ResponseEntity 객체 (201 Created)
     */
    @PostMapping("/")
    public ResponseEntity<ScheduleAndUserResponseDto> createSchedule(@Valid @RequestBody ScheduleCreateRequestDto dto) {
        return new ResponseEntity<>(scheduleService.addSchedule(dto), HttpStatus.CREATED);
    }

    /**
     * @param page 요청 페이지 번호 (0부터 시작)
     * @param size 한 페이지에 가져올 항목 수
     * @return 일정 목록 (페이징 처리됨)
     */
    @GetMapping("/")
    public ResponseEntity<List<ScheduleAndUserResponseDto>> findAllSchedule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size
    ) {
        List<ScheduleAndUserResponseDto> schedules = scheduleService.findAll(page, size);
        return ResponseEntity.ok(schedules);
    }

    /**
     * @param id 조회할 일정의 ID
     * @return 해당 ID의 일정 정보를 담은 객체를 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleAndUserResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleAndUserResponseDto scheduleResponseDtoById = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(scheduleResponseDtoById);
    }

    /**
     * @param id 수정할 일정의 id
     * @param dto 수정할 내용 및 비밀번호
     * @return 변경된 할일 반환
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto));
    }

    /**
     * @param id 삭제할 일정의 id
     * @param dto 삭제할 일정의 password
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleDeleteRequestDto dto
    ) {
        try {
            scheduleService.deleteSchedule(id, dto);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }


}
