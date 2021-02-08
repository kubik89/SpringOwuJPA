package com.vb.less.demo.controller;

import com.vb.less.demo.dto.MovieCreateDto;
import com.vb.less.demo.dto.MovieDirectorDto;
import com.vb.less.demo.dto.MovieDto;
import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import com.vb.less.demo.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
// Створює контекст лише для цього контролера, тобто запити лише цього контролера будуть використовуватись
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    // MockMvc служить як client, лише щоб виконати якийсь запит
    @Autowired
    private MockMvc mockMvc;

    // тестую GetMapping метод getMoviesByDirectorName() в контролері
//    @Test
//    public void givenDirectorNameWhenMoviesThanReturnWithMovies() throws Exception {
//        Movie movie1 = new Movie();
//        Movie movie2 = new Movie();
//        movie1.setId(1);
//        movie1.setTitle("");
//        movie1.setDuration(15);
//        movie2.setId(2);
//        movie2.setTitle("Title2");
//        movie2.setDuration(55);
//
//        String directorName = "Taras";
//        Director director = new Director();
//        director.setId(1);
//        director.setName(directorName);
//        director.setMovies(Arrays.asList(movie1, movie2));
//
//        MovieDto movieDto1 = new MovieDto(
//                movie1.getId(), movie1.getTitle(), movie1.getDuration(), directorName);
//
//        MovieDto movieDto2 = new MovieDto(
//                movie2.getId(), movie2.getTitle(), movie2.getDuration(), directorName);
//
//        MovieDirectorDto expected = new MovieDirectorDto(director.getId(), Arrays.asList(movieDto1, movieDto2));
//// описую який метод буду тестувати, це getMoviesByDirectorName
//        BDDMockito.given(movieService.getMoviesByDirectorName(ArgumentMatchers.anyString())).willReturn(expected);
//
//// в контролері ми описуємо поля, які отримуємо, бо він повертає json
//// будуємо запит: якщо зайти за посиланням де вказати імя директора із змінної, очікується, що:
//// 1 - статус відповіді буде OK
//// 2 - отримане поле director_id = 1
//// 3 - в movies[0] поле directorName = "Taras"
//        mockMvc.perform(MockMvcRequestBuilders.get("/movies/director/{name}", directorName))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.director_id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].directorName")
//                        .value("Taras"));
//    }

    //тестую PostMapping метод public MovieDto createMovie(@RequestBody @Valid MovieCreateDto movie)
// прокидаючи json (бо контроллер працює з json), викликаючи Post запит, повернути json у відповідь
//    @Test
//    public void givenMovieJsonBodyWhenCallingPostRequestThenReturnResponceWithJson() throws Exception {
//        MovieDto movie = new MovieDto(1, "Movie1", 30, "Taras");
//        BDDMockito.given(movieService.saveMovie(ArgumentMatchers.any(MovieCreateDto.class)))
//                .willReturn(movie);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
//                .content("{\n" +
//                        "        \"title\": \"Movie1\",\n" +
//                        "        \"duration\": 30,\n" +
//                        "        \"directorId\": 1\n" +
//                        "}")
//        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.directorName").value("Taras"));
//
//    }
}
