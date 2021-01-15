package com.vb.less.demo.validation;

import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// в <UniqueMovieTitle> вказуємо з якою анотацією буде співпрацювати
// в String - це тип поля який буду перевіряти (бо перевіряю поле title)
public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    private MovieRepository movieRepository;

    @Autowired
    public UniqueMovieTitleValidator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void initialize(UniqueMovieTitle constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Movie movie = movieRepository.findByTitle(value);
        if (movie == null) {
            return true;
        } else {
            return false;
        }
    }
}
