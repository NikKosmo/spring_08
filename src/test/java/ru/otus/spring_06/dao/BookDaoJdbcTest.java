package ru.otus.spring_06.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring_06.NamedParameterTemplateFactory;
import ru.otus.spring_06.damain.Author;
import ru.otus.spring_06.damain.Book;
import ru.otus.spring_06.damain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Transactional
public class BookDaoJdbcTest {

    private BookDao dao;
    private Book targetBook;

    @Before
    public void setUp() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = NamedParameterTemplateFactory
                .getNamedParameterJdbcTemplate("schema.sql", "data.sql");

        dao = new BookDaoJdbc(namedParameterJdbcTemplate,
                new GenreDaoJdbc(namedParameterJdbcTemplate),
                new AuthorDaoJdbc(namedParameterJdbcTemplate));
        targetBook = new Book();
        targetBook.setId(1);
        targetBook.setName("Clean code");
        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Fairy tale");
        Author author = new Author();
        author.setId(1);
        author.setName("Uncle Bob");
        targetBook.setGenre(genre);
    }

    @Test
    public void count() {
        assertEquals(1, dao.count());
    }

    @Test
    public void insert() {
        Book book = new Book();
        book.setId(2);
        book.setName("new name");
        Genre genre = new Genre();
        genre.setId(1);
        book.setGenre(genre);
        Author author = new Author();
        author.setId(1);
        author.setName("Uncle Bob");
        book.getAuthors().add(author);
        dao.insert(book);
        Book savedBook = dao.getById(2);
        System.out.println(savedBook);
    }

    @Test
    public void delete() {
        dao.delete(1);
        assertNull(dao.getById(1));
    }

    @Test
    public void getById() {
        Book book = dao.getById(1);
        assertEquals(targetBook.getId(), book.getId());
        assertEquals(targetBook.getName(), book.getName());
        assertEquals(targetBook.getGenre().getId(), book.getGenre().getId());
        assertEquals(targetBook.getGenre().getName(), book.getGenre().getName());
        assertEquals(1, book.getAuthors().size());
        Author author = book.getAuthors().get(0);
        assertEquals(1, author.getId());
        assertEquals("Uncle Bob", author.getName());
    }

    @Test
    public void getAll() {
        List<Book> books = dao.getAll();
        assertEquals(1, books.size());
    }
}