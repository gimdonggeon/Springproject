package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestSchedule) {

        Schedule schedule = new Schedule(
                null, requestSchedule.getName(), requestSchedule.getpw(), requestSchedule.getContents(),
                LocalDateTime.now(), LocalDateTime.now()
        );

        Schedule savedSchedule = scheduleRepository.createSchedule(schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(savedSchedule), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAllSchedules();
        List<ScheduleResponseDto> responseList = schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleRepository.findScheduleById(id);

        return schedule.map(s -> new ResponseEntity<>(new ScheduleResponseDto(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        Optional<Schedule> scheduleOpt = scheduleRepository.findScheduleById(id);

        if (scheduleOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Schedule schedule = scheduleOpt.get();
        schedule.update(requestDto.getName(), requestDto.getpw(), requestDto.getContents());
        scheduleRepository.updateSchedule(schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        Optional<Schedule> scheduleOpt = scheduleRepository.findScheduleById(id);

        if (scheduleOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        scheduleRepository.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
