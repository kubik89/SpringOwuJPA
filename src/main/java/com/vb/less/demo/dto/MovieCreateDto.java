package com.vb.less.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MovieCreateDto {
    private String title;
    private int duration;
    private int directorId;
}
