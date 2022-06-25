package com.pankov.roadtosenior.dao.jpa;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import com.pankov.roadtosenior.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        List<Movie> movieList = dao.getAllMovie(Collections.emptyMap());
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

        assertFalse(result);
    }

    @Test
    @DataSet(value = {"datasets/movie.xml", "datasets/genre.xml", "datasets/movie_2_genre.xml"})
    public void testGetMovieByGenre() {
        List<Movie> movieListWithGenreId1 = dao.getMoviesByGenre(1, Collections.emptyMap());
        List<Movie> movieListWithGenreId2 = dao.getMoviesByGenre(2, Collections.emptyMap());
        List<Movie> movieListWithGenreId3 = dao.getMoviesByGenre(3, Collections.emptyMap());

        assertEquals(3, movieListWithGenreId1.size());
        assertEquals(2, movieListWithGenreId2.size());
        assertEquals(3, movieListWithGenreId3.size());
    }

    @Test
    @DisplayName("Get all movies sorting by price asc (dao layer)")
    @DataSet(value = {"datasets/movie.xml"}, disableConstraints = true)
    public void testGetAllMoviesWithSortByPriceASC() {
        List<Movie> movieList = dao.getAllMovie(Map.of("price", "asc"));
        assertEquals(4, movieList.size());
        assertEquals(List.of(123.45, 134.67, 150.50, 200.60), movieList.stream().map(Movie::getPrice).toList());
    }

    @Test
    @DisplayName("Get all movies sorting by price desc (dao layer)")
    @DataSet(value = {"datasets/movie.xml"}, disableConstraints = true)
    public void testGetAllMoviesWithSortByPriceDESC() {
        List<Movie> movieList = dao.getAllMovie(Map.of("price", "desc"));
        assertEquals(4, movieList.size());
        assertEquals(List.of(200.60, 150.50, 134.67, 123.45), movieList.stream().map(Movie::getPrice).toList());
    }

    @Test
    @DisplayName("Get all movies sorting by rating desc (dao layer)")
    @DataSet(value = {"datasets/movie.xml"}, disableConstraints = true)
    public void testGetAllMoviesWithSortByRatingDESC() {
        List<Movie> movieList = dao.getAllMovie(Map.of("rating", "desc"));
        assertEquals(4, movieList.size());
        assertEquals(List.of(8.89, 8.88, 8.7, 8.6), movieList.stream().map(Movie::getRating).toList());
    }
}