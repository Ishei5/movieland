package com.pankov.roadtosenior.dao.jpa;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import com.pankov.roadtosenior.dao.jpa.JpaMovieDao;
import com.pankov.roadtosenior.entity.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

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
    @DataSet(value = {"datasets/movie.xml"})
    @ExpectedDataSet(value = {"datasets/movie_expected.xml"})
    public void testGetAllMovies() {
        List<Movie> movieList = dao.getAllMovie();
        assertThat(movieList.size(), equalTo(4));
    }

    @Test
    @DisplayName("Get 3 random movies (dao layer)")
    @DataSet(value = {"datasets/movie.xml"})
    //TODO: there is a chance of coincidence of random ids
    public void testGet3RandomMovies() {
        List<Movie> movieListFirstAttempt = dao.getThreeRandomMovie();
        List<Movie> movieListSecondAttempt = dao.getThreeRandomMovie();
        assertThat(movieListFirstAttempt.size(), equalTo(3));

        Set<Integer> idSFromFirstAttempt = movieListFirstAttempt.stream().map(Movie::getId).collect(Collectors.toSet());
        Set<Integer> idSFromSecondAttempt = movieListSecondAttempt.stream().map(Movie::getId).collect(Collectors.toSet());
        assertThat(idSFromFirstAttempt, not(idSFromSecondAttempt));
    }
}