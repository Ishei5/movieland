package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.dao.jpa.JpaMovieDao;
import com.pankov.roadtosenior.dto.MovieDTO;
import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.impl.MovieServiceDefaultImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    private final MovieDao movieDao = mock(JpaMovieDao.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final MovieService movieService = new MovieServiceDefaultImpl(movieDao, modelMapper);

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

    @DisplayName("Get all movies (Service layer)")
    @Test
    void testGetAllMovies() {
        when(movieDao.getAllMovie()).thenReturn(movies);
        List<MovieDTO> movieList = movieService.getAllMovie();
        assertEquals(3, movieList.size());
    }

    @DisplayName("Get 3 random movies (Service layer)")
    @Test
    void testGet3RandomMovies() {
        when(movieDao.getThreeRandomMovie()).thenReturn(movies);
        List<MovieDTO> movieList = movieService.getThreeRandomMovie();
        assertEquals(3, movieList.size());
    }

    @DisplayName("Get movies by genre id (Service layer)")
    @Test
    void testGetMoviesById() {
         when(movieDao.getMoviesByGenre(anyInt())).thenReturn(movies);
        List<MovieDTO> movieList = movieService.getMoviesByGenre(1);
        assertEquals(3, movieList.size());
    }
}