package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.dto.GenreDTO;
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
    private final List<GenreDTO> expectedGenres = List.of(
            new GenreDTO(1, "Драма"),
            new GenreDTO(2, "Криминал"),
            new GenreDTO(3, "Фэнтези"),
            new GenreDTO(4, "Детектив"),
            new GenreDTO(5, "Мелодрама"),
            new GenreDTO(6, "Биография"),
            new GenreDTO(7, "Комедия"),
            new GenreDTO(8, "Фантастика"),
            new GenreDTO(9, "Боевик"),
            new GenreDTO(10, "Триллер"),
            new GenreDTO(11, "Приключения"),
            new GenreDTO(12, "Аниме"),
            new GenreDTO(13, "Мультфильм"),
            new GenreDTO(14, "Семейный"),
            new GenreDTO(15, "Вестерн"));

    @Test
    public void testGetAllGenres() throws Exception {
        when(genreService.getAllGenres()).thenReturn(expectedGenres);
        mockMvc.perform(get("/genre")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)));
    }
}
