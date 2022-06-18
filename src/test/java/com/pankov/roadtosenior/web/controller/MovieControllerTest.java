package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovieControllerTest {
    private MockMvc mockMvc;
    private MovieService movieService;
    private final Movie firstMovie = Movie.builder()
            .id(1)
            .nameNative("First movie")
            .nameRussian("Первое кино")
            .yearOfRelease(LocalDate.of(2022, 1, 1))
            .rating(99.8)
            .price(99.8)
            .build();

    private List<Movie> movies;

    @BeforeEach
    public void setUp() {
        movieService = mock(MovieService.class);
        movies = List.of(firstMovie);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MovieController(movieService))
                .build();
    }

    @DisplayName("Get all movies (view layer)")
    @Test
    public void testGetAllMovies() throws Exception {
        byte[] expectedJson = ("[{\"id\":1,\"nameRussian\":\"Первое кино\",\"nameNative\":\"First movie\"," +
                "\"yearOfRelease\":\"2022\",\"rating\":99.8,\"price\":99.8,\"picturePath\":null}]")
                .getBytes(StandardCharsets.UTF_8);

        when(movieService.getAllMovie()).thenReturn(movies);
        mockMvc.perform(get("/movie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().bytes(expectedJson));

        verify(movieService, times(1)).getAllMovie();
    }
}