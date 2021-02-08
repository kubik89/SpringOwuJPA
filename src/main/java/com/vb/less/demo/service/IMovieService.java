package com.vb.less.demo.service;

import com.vb.less.demo.dto.MovieCreateDto;
import com.vb.less.demo.dto.MovieDirectorDto;
import com.vb.less.demo.dto.MovieDto;
import com.vb.less.demo.dto.MoviePageDto;
import com.vb.less.demo.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

public interface IMovieService {

//    MovieDto saveMovie(MovieCreateDto movie, MultipartFile file);
    MovieDto saveMovie(MovieCreateDto movie);

//    List<MovieDto> getAllMovies();

//    List<MovieDto> getAllMovies(PageRequest pageRequest);
    MoviePageDto getAllMovies();

    MovieDto getMovieById(int id);

    byte[] getMoviePoster(int id);

    void deleteMovie(int id);

    Movie updateMovie(int id, Movie movie);

    MovieDirectorDto getMoviesByDirectorName(String name);
}
