package com.pankov.roadtosenior.dao.jpa;

import com.pankov.roadtosenior.dao.GenreDao;
import com.pankov.roadtosenior.entity.Genre;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaGenreDao implements GenreDao {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public List<Genre> getAllGenres() {
        return entityManager.createQuery("from Genre", Genre.class).getResultList();
    }
}
