package com.pankov.roadtosenior.dao.jpa;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import com.pankov.roadtosenior.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DBUnitExtension.class)
class JpaMovieDaoTest {

    private static ConnectionHolder connectionHolder = () -> EntityManagerProvider.instance("junit5-pu").clear().connection();
    private JpaMovieDao dao;

    @BeforeEach
    @DataSet(cleanBefore = true)
    public void setUpOne() {
        EntityManager entityManager = EntityManagerProvider.em();
        dao = new JpaMovieDao();
        dao.setEntityManager(entityManager);
    }

    @Test
    @DisplayName("Get all movies (dao layer)")
    @DataSet(value = {"datasets/movie.xml"}, disableConstraints = true)
    @ExpectedDataSet(value = {"datasets/movie_expected.xml"})
    public void testGetAllMovies() {
        List<Movie> movieList = dao.getAllMovie();
        assertThat(movieList.size(), equalTo(4));
    }

    @Test
    @DisplayName("Get 3 random movies (dao layer)")
    @DataSet(value = {"datasets/movie.xml"})
    public void testGet3RandomMovies() {
        List<Movie> movieListFirstAttempt = dao.getThreeRandomMovie();
        List<Movie> movieListSecondAttempt = dao.getThreeRandomMovie();
        List<Movie> movieListThirdAttempt = dao.getThreeRandomMovie();
        assertThat(movieListFirstAttempt.size(), equalTo(3));

        boolean result = movieListFirstAttempt.equals(movieListSecondAttempt) ||
                movieListThirdAttempt.equals(movieListSecondAttempt);

        assertEquals(false, result);
    }

    @Test
    @DataSet(value = {"datasets/movie.xml", "datasets/genre.xml", "datasets/movie_2_genre.xml"})
    public void testGetMovieByGenre() {
        List<Movie> movieListWithGenreId1 = dao.getMoviesByGenre(1);
        List<Movie> movieListWithGenreId2 = dao.getMoviesByGenre(2);
        List<Movie> movieListWithGenreId3 = dao.getMoviesByGenre(3);

        assertEquals(3, movieListWithGenreId1.size());
        assertEquals(2, movieListWithGenreId2.size());
        assertEquals(3, movieListWithGenreId3.size());
    }
}