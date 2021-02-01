package com.vb.less.demo.service;

import com.vb.less.demo.dao.DirectoryRepository;
import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.dto.MovieDirectorDto;
import com.vb.less.demo.dto.MovieDto;
import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovieServiceClassTest {

//  @Mock - Моки, це обєкти, поведінку яких ми будемо описувати
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private DirectoryRepository directoryRepository;

// @InjectMocks - вказуємо, що всі Моки вказані вище реально в класі створюються, тобто тестую цей клас
    @InjectMocks
    private MovieService movieService;

    @Test
    public void givenDirectorNameWhenGettingMoviesByDirectorNameThenReturnMovieDirectorDto() {
// коли запуститься тестування методу getMoviesByDirectorName, то незважаючи що прийде, прокинути йому в
// аргументи дані що нижче.
// перший метод в цьому методі це findMoviesByDirectorName
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setId(1);
        movie1.setTitle("");
        movie1.setDuration(15);
        movie2.setId(2);
        movie2.setTitle("Title2");
        movie2.setDuration(55);

        String directorName = "Taras";
        Director director = new Director();
        director.setId(1);
        director.setName(directorName);
        director.setMovies(Arrays.asList(movie1, movie2));

        MovieDto movieDto1 = new MovieDto(
                movie1.getId(), movie1.getTitle(), movie1.getDuration(), directorName);

        MovieDto movieDto2 = new MovieDto(
                movie2.getId(), movie2.getTitle(), movie2.getDuration(), directorName);

        Mockito.when(directoryRepository.findMoviesByDirectorName(ArgumentMatchers.anyString()))
                .thenReturn(director);
// описуємо, що очікуємо
        MovieDirectorDto expected = new MovieDirectorDto(1, Arrays.asList(movieDto1, movieDto2));
// описуємо реальний результат
        MovieDirectorDto actual = movieService.getMoviesByDirectorName(directorName);
// порівнюємо чи певні значення що отримали дорівнюють чомусь, в чому стоїть завдання
        Assertions.assertThat(actual.getMovies()).size().isEqualTo(2);
        Assertions.assertThat(actual.getDirectorId()).isEqualTo(expected.getDirectorId());
        Assertions.assertThat(actual.getMovies().get(1).getTitle().length()<=100).isTrue();
//        Assertions.assertThat(actual.getMovies().get(0).getTitle()).isNull();
    }
}
