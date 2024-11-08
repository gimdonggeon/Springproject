package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    String name;
    String contents;
    Long pw;

    public ScheduleRequestDto(String name, Long pw, String contents) {
        this.name = name;
        this.pw = pw;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpw() {
        return pw;
    }

    public void setPw(Long pw) {
        this.pw = pw;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}


