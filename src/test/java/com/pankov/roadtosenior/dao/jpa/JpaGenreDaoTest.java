package com.pankov.roadtosenior.dao.jpa;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import com.pankov.roadtosenior.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DBUnitExtension.class)
class JpaGenreDaoTest {

    private final Set<String> expectedGenres = Set.of("Драма", "Криминал", "Фэнтези", "Детектив", "Мелодрама", "Биография", "Комедия", "Фантастика",
            "Боевик", "Триллер", "Приключения", "Аниме", "Мультфильм", "Семейный", "Вестерн");
    private ConnectionHolder connectionHolder = () -> EntityManagerProvider.instance("junit5-pu").clear().connection();
    private JpaGenreDao dao;

    @BeforeEach
    @DataSet(cleanBefore = true)
    public void setUpOne() {
        EntityManager entityManager = EntityManagerProvider.em();
        dao = new JpaGenreDao();
        dao.setEntityManager(entityManager);
    }

    @Test
    @DisplayName("Get all genres (dao layer)")
    @DataSet(value = {"datasets/genre.xml"}, disableConstraints = true)
    public void testGetAllGenres() {
        List<Genre> genreList = dao.getAllGenres();
        assertEquals(15, genreList.size());

        genreList.removeIf(genre -> expectedGenres.contains(genre.getName()));

        assertEquals(0, genreList.size());
    }
}