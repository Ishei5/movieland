package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.dto.MovieDTO;
import com.pankov.roadtosenior.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping(value = "/random")
    public List<MovieDTO> getThreeRandomMovie() {
        return movieService.getThreeRandomMovie();
    }

    @GetMapping(value = "/genre/{genreId}")
    public List<MovieDTO> getMovieByGenre(@PathVariable int genreId) {
        return movieService.getMoviesByGenre(genreId);
    }


}
