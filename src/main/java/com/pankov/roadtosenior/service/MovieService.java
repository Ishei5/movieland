package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovie();

    List<MovieDTO> getThreeRandomMovie();

    List<MovieDTO> getMoviesByGenre(int genreId);
}
