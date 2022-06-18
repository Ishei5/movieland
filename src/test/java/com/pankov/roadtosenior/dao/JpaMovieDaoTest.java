package com.pankov.roadtosenior.dao;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import com.pankov.roadtosenior.dao.jpa.JpaMovieDao;
import com.pankov.roadtosenior.entity.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(DBUnitExtension.class)
class JpaMovieDaoTest {

    private static ConnectionHolder connectionHolder = () -> EntityManagerProvider.instance("junit5-pu").clear().connection();
    private static JpaMovieDao dao;

    @BeforeAll
    @DataSet(cleanBefore = true)
    public static void setUpOne() {
        EntityManager entityManager = EntityManagerProvider.em();
        dao = new JpaMovieDao();
        dao.setEntityManager(entityManager);
    }

    @Test
    @DisplayName("Get all movies (dao layer)")
    @DataSet(value = {"datasets/movie.xml"})
    public void testGetAllMovies() {
        List<Movie> movieList = dao.getAllMovie();
        assertThat(movieList.size(), equalTo(2));
    }
}