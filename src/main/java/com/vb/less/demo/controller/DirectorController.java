package com.vb.less.demo.controller;

import com.vb.less.demo.entity.Director;
import com.vb.less.demo.service.IDirectorService;
import com.vb.less.demo.service.IDirectorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// @RequestMapping можна вказувати якщо value всіх методів рівні цьому посиланні = "/directors"
@RequestMapping(value = "/directors")
@RestController
@Slf4j
public class DirectorController {

    @Autowired
    private IDirectorService iDirectorService;

    //це поле для Логів, яка застосується для DirectorController (бо в ньому всі методи для Director)
    private static Logger logger = LoggerFactory.getLogger(DirectorController.class);


//    анотація автоматично створить обєкт для методу
//@RequestMapping - старіша аніж @GetMapping, бо потребує вказати метод в параметрах

    //    @RequestMapping(value = "/directors", method = RequestMethod.GET)
    @GetMapping()
    public List<Director> getAllDirectors() {
        return iDirectorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public Director getDirector(@RequestParam int id) {
        return iDirectorService.getDirectorById(id);
    }

    @PostMapping()
    public Director createDirector(@RequestBody Director director) {
//        logger.info("Logger: this director was created: {}", director.getTitle());
        log.info("Logger: this director was created: {}", director.getName());
        return iDirectorService.saveDirector(director);
    }

    @PutMapping(value = "/{id}")
    public Director updateDirector(@PathVariable int id, @RequestBody @Valid Director director) {
        return iDirectorService.updateDirector(id, director);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable int id) {
        iDirectorService.deleteDirector(id);
    }
}
