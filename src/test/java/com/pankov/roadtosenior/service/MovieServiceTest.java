package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dao.jpa.JpaMovieDao;
import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.impl.MovieServiceDefaultImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {
    private static MovieServiceDefaultImpl movieService;
    private static JpaMovieDao jpaMovieDao;
    private final Movie firstMovie = Movie.builder()
            .id(1)
            .nameNative("First movie")
            .nameRussian("Первое кино")
            .yearOfRelease(LocalDate.of(2022, 1, 1))
            .rating(99.8)
            .price(99.8)
            .picturePath("https://picture1.jpg")
            .build();
    private final Movie secondMovie = Movie.builder()
            .id(2)
            .nameNative("Second movie")
            .nameRussian("Второе кино")
            .yearOfRelease(LocalDate.of(2022, 2, 1))
            .rating(99.9)
            .price(99.9)
            .picturePath("https://picture2.jpg")
            .build();
    private final Movie thirdMovie = Movie.builder()
            .id(3)
            .nameNative("Third movie")
            .nameRussian("Третье кино")
            .yearOfRelease(LocalDate.of(2022, 2, 2))
            .rating(99.1)
            .price(99.1)
            .picturePath("https://picture3.jpg")
            .build();

    private List<Movie> movies =  List.of(firstMovie, secondMovie, thirdMovie);

    @BeforeAll
    public static void setUp() {
        jpaMovieDao = mock(JpaMovieDao.class);
        movieService = new MovieServiceDefaultImpl(jpaMovieDao);
    }

    @DisplayName("Get all movies (Service layer)")
    @Test
    void testGetAllMovies() {
        when(jpaMovieDao.getAllMovie()).thenReturn(movies);
        List<Movie> movieList = movieService.getAllMovie();
        assertEquals(3, movieList.size());
        assertTrue(movieList.contains(firstMovie));
        assertTrue(movieList.contains(secondMovie));
        assertTrue(movieList.contains(thirdMovie));
    }

    @DisplayName("Get 3 random movies (Service layer)")
    @Test
    void testGet3Randomovies() {
        when(jpaMovieDao.getThreeRandomMovie()).thenReturn(movies);
        List<Movie> movieList = movieService.getThreeRandomMovie();
        assertEquals(3, movieList.size());
        assertTrue(movieList.contains(firstMovie));
        assertTrue(movieList.contains(secondMovie));
        assertTrue(movieList.contains(thirdMovie));
    }
}