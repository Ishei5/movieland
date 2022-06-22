package com.pankov.roadtosenior.service.impl;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceDefaultImpl implements MovieService {

    private MovieDao movieDao;

    @Override
    public List<Movie> getAllMovie() {
        return movieDao.getAllMovie();
    }

    @Override
    public List<Movie> getThreeRandomMovie() {
        return movieDao.getThreeRandomMovie();
    }
}
