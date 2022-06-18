package com.pankov.roadtosenior.dao.jpa;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.entity.Movie;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaMovieDao implements MovieDao {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> getAllMovie() {
        return entityManager.createQuery("from Movie", Movie.class).getResultList();
    }
}
