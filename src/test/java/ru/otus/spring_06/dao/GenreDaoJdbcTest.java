package ru.otus.spring_06.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring_06.NamedParameterTemplateFactory;
import ru.otus.spring_06.damain.Genre;

import static org.junit.Assert.assertEquals;

@Transactional
public class GenreDaoJdbcTest {

    private GenreDao dao;
    private Genre targetGenre;

    @Before
    public void setUp() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = NamedParameterTemplateFactory
                .getNamedParameterJdbcTemplate("schema.sql", "data.sql");

        dao = new GenreDaoJdbc(namedParameterJdbcTemplate);
        targetGenre = new Genre();
        targetGenre.setId(1);
        targetGenre.setName("Fairy tale");
    }

    @Test
    public void getById() {
        Genre genre = dao.getById(1);
        assertEquals(targetGenre.getId(), genre.getId());
        assertEquals(targetGenre.getName(), genre.getName());
    }
}