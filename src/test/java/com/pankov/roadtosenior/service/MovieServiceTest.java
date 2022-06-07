package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dao.JpaMovieDao;
import com.pankov.roadtosenior.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {
    private MovieService movieService;
    private JpaMovieDao jpaMovieDao;

    private final Movie firstMovie = Movie.builder()
            .id(1)
            .nameNative("First movie")
            .nameRussian("Первое кино")
            .yearOfRelease(LocalDate.of(2022, 1, 1))
            .rating(99.8)
            .price(99.8)
            .build();
    ;
    private final Movie secondMovie = Movie.builder()
            .id(2)
            .nameNative("Second movie")
            .nameRussian("Второе кино")
            .yearOfRelease(LocalDate.of(2022, 2, 1))
            .rating(99.9)
            .price(99.9)
            .build();

    private List<Movie> movies;

    @BeforeEach
    public void setUp() {
        jpaMovieDao = mock(JpaMovieDao.class);
        movieService = new MovieService(jpaMovieDao);
        movies = List.of(firstMovie, secondMovie);
    }

    @DisplayName("Get all movies (Service layer)")
    @Test
    void testGetAllMovies() {
        when(jpaMovieDao.getAllMovie()).thenReturn(movies);
        List<Movie> movieList = movieService.getAllMovie();
        assertEquals(2, movieList.size());
        assertTrue(movieList.contains(firstMovie));
        assertTrue(movieList.contains(secondMovie));
    }
}