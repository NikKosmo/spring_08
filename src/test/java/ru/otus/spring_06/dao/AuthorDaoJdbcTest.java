package ru.otus.spring_06.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring_06.NamedParameterTemplateFactory;
import ru.otus.spring_06.damain.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class AuthorDaoJdbcTest {

    private AuthorDao dao;
    private Author targetAuthor;

    @Before
    public void setUp() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = NamedParameterTemplateFactory
                .getNamedParameterJdbcTemplate("schema.sql", "data.sql");

        dao = new AuthorDaoJdbc(namedParameterJdbcTemplate);
        targetAuthor = new Author();
        targetAuthor.setId(1);
        targetAuthor.setName("Uncle Bob");
    }

    @Test
    public void getById() {
        Author author = dao.getById(1);
        assertEquals(targetAuthor.getId(), author.getId());
        assertEquals(targetAuthor.getName(), author.getName());
    }

    @Test
    public void findBookAuthors() {
        List<Author> authors = dao.findBookAuthors(1);
        assertEquals(1, authors.size());
        Author author = authors.get(0);
        assertEquals(targetAuthor.getId(), author.getId());
        assertEquals(targetAuthor.getName(), author.getName());
    }
}