package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.dto.MovieDTO;
import com.pankov.roadtosenior.service.MovieService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class MovieControllerTest {
    private static MockMvc mockMvc;
    private static MovieService movieService;
    private final MovieDTO firstMovie = MovieDTO.builder()
            .id(1)
            .nameNative("First movie")
            .nameRussian("Первое кино")
            .yearOfRelease(LocalDate.of(2022, 1, 1))
            .rating(99.8)
            .price(99.8)
            .picturePath("https://picture1.jpg")
            .build();
    private final MovieDTO secondMovie = MovieDTO.builder()
            .id(2)
            .nameNative("Second movie")
            .nameRussian("Второе кино")
            .yearOfRelease(LocalDate.of(2022, 2, 1))
            .rating(99.9)
            .price(99.9)
            .picturePath("https://picture2.jpg")
            .build();
    private final MovieDTO thirdMovie = MovieDTO.builder()
            .id(3)
            .nameNative("Third movie")
            .nameRussian("Третье кино")
            .yearOfRelease(LocalDate.of(2022, 2, 2))
            .rating(99.1)
            .price(99.1)
            .picturePath("https://picture3.jpg")
            .build();

    private final List<MovieDTO> movies = List.of(firstMovie, secondMovie, thirdMovie);
    private final String expectedJSONString =
            "[{\"id\":1,\"nameRussian\":\"Первое кино\",\"nameNative\":\"First movie\",\"yearOfRelease\":\"2022\"," +
            "\"rating\":99.8,\"price\":99.8,\"picturePath\":\"https://picture1.jpg\"}, " +
            "{\"id\":2,\"nameRussian\":\"Второе кино\",\"nameNative\":\"Second movie\",\"yearOfRelease\":\"2022\"," +
            "\"rating\":99.9,\"price\":99.9,\"picturePath\":\"https://picture2.jpg\"}," +
            "{\"id\":3, \"nameRussian\":\"Третье кино\",\"nameNative\":\"Third movie\",\"yearOfRelease\":\"2022\"," +
            "\"rating\":99.1,\"price\":99.1,\"picturePath\":\"https://picture3.jpg\"}]";

    @BeforeAll
    public static void setUp() {
        movieService = mock(MovieService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MovieController(movieService))
                .build();
    }

    @DisplayName("Get all movies (view layer)")
    @Test
    public void testGetAllMovies() throws Exception {
        when(movieService.getAllMovie()).thenReturn(movies);
        mockMvc.perform(get("/movie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(expectedJSONString));

        verify(movieService, times(1)).getAllMovie();
    }

    @DisplayName("Get 3 random movies (view layer)")
    @Test
    public void testGetRandomMovies() throws Exception {
        when(movieService.getThreeRandomMovie()).thenReturn(movies);
        mockMvc.perform(get("/movie/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(expectedJSONString));

        verify(movieService, times(1)).getThreeRandomMovie();
    }

    @DisplayName("Get movies by genre id (web layer)")
    @Test
    public void testGetMoviesByGenreId() throws Exception {
        when(movieService.getMoviesByGenre(anyInt())).thenReturn(movies);
        mockMvc.perform(get("/movie/genre/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(expectedJSONString));

        verify(movieService, times(1)).getMoviesByGenre(anyInt());
    }
}