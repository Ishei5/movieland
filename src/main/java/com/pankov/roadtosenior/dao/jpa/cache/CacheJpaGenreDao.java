package com.pankov.roadtosenior.dao.jpa.cache;

import com.pankov.roadtosenior.dao.GenreDao;
import com.pankov.roadtosenior.dao.jpa.JpaGenreDao;
import com.pankov.roadtosenior.entity.Genre;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.persistence.PostLoad;
import java.util.List;

@Repository
@Primary
@NoArgsConstructor
@EnableScheduling
@Slf4j
public class CacheJpaGenreDao implements GenreDao {

    @Autowired
    private JpaGenreDao genreDao;
    private volatile List<Genre> cachedGenreList;

    public CacheJpaGenreDao(JpaGenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = cachedGenreList;
        return genres;
    }

    @PostLoad
    void init() {
        cachedGenreList = genreDao.getAllGenres();
        log.info("Cached genre list");
    }

    @Scheduled(fixedRateString = "${fixedRate}")
    private void updateCachedGenreList() {
        log.info("Genre cache updated");
        init();
    }
}
