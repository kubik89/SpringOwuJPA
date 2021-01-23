package com.vb.less.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor

// це клас який повертає самі те поля, які нам треба для таблиці бази даних.
// рекомендується створювати новий клас ТІЛЬКИ з тими даними які потрібні для таблиці і повертати його
    public class MovieDto {

        private int movieId;
        private String title;
        private int duration;
        private String directorName;
    }
