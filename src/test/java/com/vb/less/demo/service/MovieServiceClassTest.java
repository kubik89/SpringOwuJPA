package com.vb.less.demo.service;

import com.vb.less.demo.dao.DirectoryRepository;
import com.vb.less.demo.dao.MovieRepository;
import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        movie1.setTitle("Title1");
        movie2.setId(2);
        movie2.setTitle("Title2");

        Director director = new Director();
        director.setId(1);
        director.setName("Taras");
        Mockito.when(directoryRepository.findMoviesByDirectorName(ArgumentMatchers.anyString()))
                .thenReturn(director);

    }


}
