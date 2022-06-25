package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dto.MovieDTO;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<MovieDTO> getAllMovie(Map<String,String> params);

    List<MovieDTO> getThreeRandomMovie();

    List<MovieDTO> getMoviesByGenre(int genreId, Map<String,String> params);
}
