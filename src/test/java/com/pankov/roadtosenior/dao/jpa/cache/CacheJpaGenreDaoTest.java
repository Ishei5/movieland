package com.pankov.roadtosenior.dao.jpa.cache;

import com.pankov.roadtosenior.dao.jpa.JpaGenreDao;
import com.pankov.roadtosenior.entity.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CacheJpaGenreDaoTest {

    private final JpaGenreDao genreDao = mock(JpaGenreDao.class);
    private final CacheJpaGenreDao cacheJpaGenreDao = new CacheJpaGenreDao(genreDao);

    @Test
    public void testCachedGetAllGenre() {
        when(genreDao.getAllGenres()).thenReturn(new ArrayList<>(0));
        cacheJpaGenreDao.init();
        List<Genre> allGenres1 = cacheJpaGenreDao.getAllGenres();
        List<Genre> allGenres2 = cacheJpaGenreDao.getAllGenres();
        verify(genreDao, times(1)).getAllGenres();
    }
}