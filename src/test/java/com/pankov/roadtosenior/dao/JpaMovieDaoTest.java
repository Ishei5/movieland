package com.pankov.roadtosenior.dao;

import com.pankov.roadtosenior.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JpaMovieDaoTest {
    private JpaMovieDao jpaMovieDao;
    private SessionFactory sessionFactory;
    private Session session;
    private Query query;

    @BeforeEach
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        jpaMovieDao = new JpaMovieDao(sessionFactory);
        session = mock(Session.class);
        query = mock(Query.class);
    }

    @DisplayName("Get all movies (dao layer)")
    @Test
    void testGetAllMovies() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("from Movie")).thenReturn(query);
        List<Movie> movieList = new ArrayList<>(1);
        when(query.list()).thenReturn(movieList);
    }

}