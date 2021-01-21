package com.vb.less.demo.controller;

import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.entity.Movie;
import com.vb.less.demo.service.IMovieService;
import com.vb.less.demo.validation.MovieValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @RequestMapping можна вказувати якщо value всіх методів рівні цьому посиланні = "/movies"
@RequestMapping(value = "/movies")
@RestController
@Slf4j
public class MovieController {

    @Autowired
    private IMovieService iMovieService;

//це поле для Логів, яка застосується для MovieController (бо в ньому всі методи для Movie)
    private static Logger logger = LoggerFactory.getLogger(MovieController.class);


//    анотація автоматично створить обєкт для методу
//@RequestMapping - старіша аніж @GetMapping, бо потребує вказати метод в параметрах

    //    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping()
    public List<Movie> getAllMovies() {
        return iMovieService.getAllMovies();
    }

// тут @RequestParam як приклад, але правильно писати через @PathVariable
    @GetMapping("/movie")
    public Movie getMovie(@RequestParam int id) {
        return iMovieService.getMovieById(id);
    }

    // @RequestBody - оскільки ми json це як стрінга, то цією анотацією ми перетворюємо стрічку в
    // обєкт який в параметрах біля анотації
    // якщо поля які ми передамо не будуть знайдені - їх значення будуть перезаписані як null
    // Valid тут перевірить, чи поля класу валідні (якщо там стоять анотації валідації для поля класу)
    @PostMapping()
    public Movie createMovie(@RequestBody @Valid Movie movie) {
//        logger.info("Logger: this movie was created: {}", movie.getTitle());
        log.info("Logger: this movie was created: {}", movie.getTitle());
        return iMovieService.saveMovie(movie);
    }

// якщо в Movie не знайдено по вхідному id що в посиланні, тоді помилка, і по-іншому - зберегти в базу
// @RequestBody Movie movie - це вхідний новий обєкт який ми буде вставлений (перезаписаний) для даної id в базі
// Тобто, якщо така id існує, то записати в неї цей обєкт movie
    @PutMapping(value = "/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
       return iMovieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        iMovieService.deleteMovie(id);
    }

// анотація, яка вказує, що якщо якийсь метод цього класу має анотацію @Valid, то виконувати саме її перевірки,
// які описані в класі вказаному в InitBinder-і
// але ці ручні валідатори спрацьовують швидше від готових (від тих що в класах на полях), тому треба
// описувати ті ж самі перевірки що на полях для цього валідатора, бо відпрацює швидше за все першим він.
    @InitBinder
    public void myInitBinder (WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new MovieValidator());
    }
}
