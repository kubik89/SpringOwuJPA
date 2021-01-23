package com.vb.less.demo.service;

import com.vb.less.demo.dao.DirectoryRepository;
import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.dto.BadRequestException;
import com.vb.less.demo.dto.MovieCreateDto;
import com.vb.less.demo.dto.MovieDto;
import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;
    private DirectoryRepository directoryRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, DirectoryRepository directoryRepository) {
        this.movieRepository = movieRepository;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public MovieDto saveMovie(MovieCreateDto movie) {
        char firstLetter = movie.getTitle().charAt(0);

        if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
            throw new BadRequestException("Value must have first CAPITAL letter");
        }

        Movie movieEntity = new Movie();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setDuration(movie.getDuration());

// якщо такий директор по id існує, то сетнути його для цього Movie
// але краще це тягну через Service
        Optional<Director> director = directoryRepository.findById(movie.getDirectorId());
//        director.ifPresent(director1 -> {
//            movieEntity.setDirector(director1);
//        });
        Director newDirector = director.orElseThrow(() ->
                new BadRequestException("no director with such id found"));
        movieEntity.setDirector(newDirector);
        return convertToMovieDto(movieRepository.saveAndFlush(movieEntity));
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movie ->
                new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(int id) {
        return convertToMovieDto(movieRepository.getOne(id));
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        if (movieRepository.existsById(id)) {
            movie.setId(id);
            return movieRepository.saveAndFlush(movie);
        } else {
            throw new IllegalArgumentException("No movie with such id found: " + id);
        }
    }

    private MovieDto convertToMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName());
    }
}
