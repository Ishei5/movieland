package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value = "/movie")
    public List<Movie> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping(value = "/movie/random")
    public List<Movie> getThreeRandomMovie() {
        return movieService.getThreeRandomMovie();
    }


}
