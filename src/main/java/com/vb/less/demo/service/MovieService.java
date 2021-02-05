package com.vb.less.demo.service;

import com.vb.less.demo.dao.DirectoryRepository;
import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.dto.*;
import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final DirectoryRepository directoryRepository;

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
// повертаю обєкт класу MoviePageDto, де отримаю ліст Movie та кількість сторінок на який його розраховано
//    @Override
//    public MoviePageDto getAllMovies(PageRequest pageRequest) {
//        Page<Movie> moviePages = movieRepository.findAll(pageRequest);
//        List<MovieDto> movies = moviePages.stream().map(movie ->
//                new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName()))
//                .collect(Collectors.toList());
//        return new MoviePageDto(movies, moviePages.getTotalPages());
//    }

    @Override
    public MoviePageDto getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> listMovieDto = movieList.stream().map(movie ->
                new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName()))
                .collect(Collectors.toList());
        return new MoviePageDto(listMovieDto);
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

    @Override
    public MovieDirectorDto getMoviesByDirectorName(String name) {
        Director director = directoryRepository.findMoviesByDirectorName(name);
        String directorName = director.getName();
        int directorId = director.getId();
        List<Movie> movies = director.getMovies();
        List<MovieDto> movieDtos = movies.stream().map(movie ->
                new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), directorName)).collect(Collectors.toList());
        return new MovieDirectorDto(directorId, movieDtos);
    }

    private MovieDto convertToMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName());
    }
}
