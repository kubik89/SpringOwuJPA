package com.vb.less.demo.service;

import com.vb.less.demo.entity.Movie;

import java.util.List;

public interface IMovieService {

    Movie saveMovie(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(int id);

    void deleteMovie(int id);

    Movie updateMovie(int id, Movie movie);
}
