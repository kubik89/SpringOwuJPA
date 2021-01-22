package com.vb.less.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// це клас який має поля, які будуть повертатися для власного Exception в класі ErrorController

@AllArgsConstructor
@Getter
public class ResponceErrorDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private int status;

}
