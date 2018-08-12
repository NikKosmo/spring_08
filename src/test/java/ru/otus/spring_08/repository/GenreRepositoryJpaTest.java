package ru.otus.spring_08.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring_08.damain.Author;
import ru.otus.spring_08.damain.Book;
import ru.otus.spring_08.damain.Genre;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.spring_08")
public class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    private Genre genre;

    @Before
    public void setUp() {
        genre = new Genre();
        genre.setName("Horror");

        Book book = new Book();
        book.setName("Shining");
        book.setGenre(genre);
        Author author = new Author();
        author.setName("Stephen King");
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    @Test
    public void getById() {
        Genre foundGenre = genreRepository.findById(genre.getId()).get();
        assertEquals(genre.getName(), foundGenre.getName());
    }

    @Test
    public void getByName() {
        Genre foundGenre = genreRepository.findByName("Horror");
        assertEquals(genre.getName(), foundGenre.getName());
    }
}