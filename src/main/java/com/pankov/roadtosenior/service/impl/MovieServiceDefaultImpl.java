package com.pankov.roadtosenior.service.impl;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.dto.MovieDTO;
import com.pankov.roadtosenior.entity.Movie;
import com.pankov.roadtosenior.service.MovieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceDefaultImpl implements MovieService {

    private final MovieDao movieDao;
    private final ModelMapper modelMapper;

    @Override
    public List<MovieDTO> getAllMovie() {
        List<Movie> allMovie = movieDao.getAllMovie();
        return modelMapper.map(allMovie, new TypeToken<List<MovieDTO>>() {
        }.getType());
    }

    @Override
    public List<MovieDTO> getThreeRandomMovie() {
        List<Movie> allMovie = movieDao.getThreeRandomMovie();
        return allMovie.stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<MovieDTO> getMoviesByGenre(int genreId) {
        List<Movie> moviesByGenre = movieDao.getMoviesByGenre(genreId);
        return moviesByGenre.stream().map(this::convertToDTO).toList();
    }

    private MovieDTO convertToDTO(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}
