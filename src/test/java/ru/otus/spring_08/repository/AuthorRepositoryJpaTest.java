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
public class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private Author author;

    @Before
    public void setUp() {
        author = new Author();
        author.setName("Stephen King");

        Book book = new Book();
        book.setName("Shining");
        Genre genre = new Genre();
        genre.setName("Horror");
        book.setGenre(genre);
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    @Test
    public void getById() {
        Author foundAuthor = authorRepository.findById(author.getId()).get();
        assertEquals(author.getName(), foundAuthor.getName());
    }

    @Test
    public void getByName() {
        Author foundAuthor = authorRepository.findByName("Stephen King");
        assertEquals(author.getName(), foundAuthor.getName());
    }
}