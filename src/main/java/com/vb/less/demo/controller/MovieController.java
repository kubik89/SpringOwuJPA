package com.vb.less.demo.controller;

import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @RequestMapping можна вказувати якщо value всіх методів рівні цьому посиланні = "/movies"
@RequestMapping(value = "/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


//    анотація автоматично створить обєкт для методу
//@RequestMapping - старіша аніж @GetMapping, бо потребує вказати метод в параметрах

    //    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping()
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // записуємо дані в ліст
    // @RequestBody - оскільки ми json це як стрінга, то цією анотацією ми перетворюємо стрічку в
    // обєкт який в параметрах біля анотації
    // якщо поля які ми передамо не будуть знайдені - їх значення будуть перезаписані як null
    @PostMapping()
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

// якщо в Movie не знайдено по вхідному id що в посиланні, тоді помилка, і по-іншому - зберегти в базу
// @RequestBody Movie movie - це вхідний новий обєкт який ми буде вставлений (перезаписаний) для даної id в базі
// Тобто, якщо така id існує, то записати в неї цей обєкт movie
    @PutMapping(value = "/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        if (movieRepository.findById(id).isPresent()) {
            movie.setId(id);
            return movieRepository.saveAndFlush(movie);
        } else {
            throw new IllegalArgumentException("No movie with such id found" + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieRepository.deleteById(id);
    }
}
