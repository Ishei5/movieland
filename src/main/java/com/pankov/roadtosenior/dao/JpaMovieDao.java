package com.pankov.roadtosenior.dao;

import com.pankov.roadtosenior.entity.Movie;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
public class JpaMovieDao implements MovieDao {

    @Autowired
    private final SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<Movie> getAllMovie() {
        Session session = sessionFactory.openSession();

        List<Movie> result = session.createQuery("from Movie").list();

        if (session.isOpen()) {
            session.close();
        }

        return result;
    }
}
