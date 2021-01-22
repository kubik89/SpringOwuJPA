package com.vb.less.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate birthdate;

//   @JoinColumn створить поле directorID_directorsList в таблиці Director і повяже їх (директора із Лістом Director)
// якщо їх декілька - то створить декілька полів і звязків як нижче показано
    @OneToMany
    @JoinColumn(name = "directorID_directorsList")
    private List<Director> directors = new ArrayList<>();
//
    @OneToMany
    @JoinColumn(name = "directorID2_directorsList")
    private List<Director> directors2 = new ArrayList<>();
}
