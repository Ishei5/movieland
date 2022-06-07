package com.pankov.roadtosenior.controller;

import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = {"/api/v1"})
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @GetMapping(value = "/movie")
    public List<Movie> getAllMovie() {
        return movieService.getAllMovie();
    }
}
