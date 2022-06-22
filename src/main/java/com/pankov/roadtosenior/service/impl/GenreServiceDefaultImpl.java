package com.pankov.roadtosenior.service.impl;

import com.pankov.roadtosenior.dao.GenreDao;
import com.pankov.roadtosenior.entity.Genre;
import com.pankov.roadtosenior.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceDefaultImpl implements GenreService {

    private GenreDao genreDao;

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}
