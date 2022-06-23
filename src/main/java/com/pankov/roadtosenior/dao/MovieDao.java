package com.pankov.roadtosenior.dao;

import com.pankov.roadtosenior.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovie();

    List<Movie> getThreeRandomMovie();

    List<Movie> getMoviesByGenre(int genreId);
}
