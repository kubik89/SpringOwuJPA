package com.vb.less.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MoviePageDto {
    private List<MovieDto> movies;
//    private int totalPages;
}
