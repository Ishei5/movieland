package com.pankov.roadtosenior.service.impl;

import com.pankov.roadtosenior.dao.GenreDao;
import com.pankov.roadtosenior.dto.GenreDTO;
import com.pankov.roadtosenior.entity.Genre;
import com.pankov.roadtosenior.service.GenreService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceDefaultImpl implements GenreService {

    private final GenreDao genreDao;
    private final ModelMapper modelMapper;

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> allGenres = genreDao.getAllGenres();
        return modelMapper.map(allGenres, new TypeToken<List<GenreDTO>>() {}.getType());
    }
}
