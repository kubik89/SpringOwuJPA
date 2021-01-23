package com.vb.less.demo.service;

import com.vb.less.demo.dto.MovieCreateDto;
import com.vb.less.demo.dto.MovieDto;
import com.vb.less.demo.entity.Movie;

import java.util.List;

public interface IMovieService {

    MovieDto saveMovie(MovieCreateDto movie);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(int id);

    void deleteMovie(int id);

    Movie updateMovie(int id, Movie movie);
}
