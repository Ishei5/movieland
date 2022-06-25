package com.pankov.roadtosenior.dao.jpa;

import com.pankov.roadtosenior.dao.MovieDao;
import com.pankov.roadtosenior.entity.Movie;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
public class JpaMovieDao implements MovieDao {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> getAllMovie(Map<String,String> params) {
        return entityManager.createQuery("from Movie m" + createOrderSql(params), Movie.class).getResultList();
    }

    @Override
    public List<Movie> getThreeRandomMovie() {
        return entityManager.createQuery("from Movie order by rand()", Movie.class)
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public List<Movie> getMoviesByGenre(int genreId, Map<String,String> params) {
        return entityManager.createQuery("select m from Movie m join m.genres g where g.id=:genreId"
                        + createOrderSql(params), Movie.class)
                .setParameter("genreId", genreId)
                .getResultList();
    }

    String createOrderSql(Map<String,String> params) {
        if (params.isEmpty()) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(", ", " order by ", "");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringJoiner.add(entry.getKey() + " " + entry.getValue());
        }

        return stringJoiner.toString();
    }
}
