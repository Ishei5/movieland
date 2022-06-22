package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.entity.Genre;
import com.pankov.roadtosenior.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<Genre> getAllGenre(){
        return genreService.getAllGenres();
    }

}
