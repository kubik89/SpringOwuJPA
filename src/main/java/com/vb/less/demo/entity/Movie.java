package com.vb.less.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vb.less.demo.validation.UniqueMovieTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// ігнорувати поле hibernateinterceptor й його подібні hibernate*. його отримуємо додатково в
// методі getOne(id) де шукаємо по id, а воно не відповідає структурі класу, тому уникаємо
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title1")
    @NotBlank
//    @UniqueMovieTitle()
    private String title;

    @Positive(message = "value must be positive")
    @Max(value = 210, message = "Too long duration")
    private int duration;

// налаштує звязок між таблицею Director і в Movie таблиці створить поле яке є ключем в Director (director_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Director director;


}
