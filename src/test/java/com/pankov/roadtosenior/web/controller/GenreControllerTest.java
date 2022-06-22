package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.entity.Genre;
import com.pankov.roadtosenior.service.GenreService;
import com.pankov.roadtosenior.service.impl.GenreServiceDefaultImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GenreControllerTest {

    private final GenreService genreService = mock(GenreServiceDefaultImpl.class);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new GenreController(genreService)).build();
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

    @Test
    public void testGetAllGenres() throws Exception {
        when(genreService.getAllGenres()).thenReturn(expectedGenres);
        mockMvc.perform(get("/genre")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)));
    }
}
