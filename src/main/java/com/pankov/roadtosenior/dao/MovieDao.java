package com.pankov.roadtosenior.dao;

import com.pankov.roadtosenior.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieDao {
    List<Movie> getAllMovie(Map<String,String> params);

    List<Movie> getThreeRandomMovie();

    List<Movie> getMoviesByGenre(int genreId, Map<String,String> params);
}
