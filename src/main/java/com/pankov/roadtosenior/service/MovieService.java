package com.pankov.roadtosenior.service;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movie> getAllMovie() {
        return movieDao.getAllMovie();
    }
}
