package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dao.GenreDao;
import com.pankov.roadtosenior.dao.jpa.JpaGenreDao;
import com.pankov.roadtosenior.entity.Genre;
import com.pankov.roadtosenior.service.impl.GenreServiceDefaultImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreServiceTest {
    private final List<Genre> expectedGenres = List.of(
            new Genre(1, "Драма"),
            new Genre(2, "Криминал"),
            new Genre(3, "Фэнтези"),
            new Genre(4, "Детектив"),
            new Genre(5, "Мелодрама"),
            new Genre(6, "Биография"),
            new Genre(7, "Комедия"),
            new Genre(8, "Фантастика"),
            new Genre(9, "Боевик"),
            new Genre(10, "Триллер"),
            new Genre(11, "Приключения"),
            new Genre(12, "Аниме"),
            new Genre(13, "Мультфильм"),
            new Genre(14, "Семейный"),
            new Genre(15, "Вестерн"));

    private final GenreDao genreDao = mock(JpaGenreDao.class);
    private final GenreService genreService = new GenreServiceDefaultImpl(genreDao);

    @Test
    public void testGetAllGenres() {
        when(genreDao.getAllGenres()).thenReturn(expectedGenres);
        List<Genre> allGenres = genreService.getAllGenres();
        assertEquals(15, allGenres.size());
    }
}
