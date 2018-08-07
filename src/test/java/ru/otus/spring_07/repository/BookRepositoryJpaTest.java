package ru.otus.spring_07.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring_07.damain.Author;
import ru.otus.spring_07.damain.Book;
import ru.otus.spring_07.damain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.spring_07")
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
        book.setName("Shining");
        Genre genre = new Genre();
        genre.setName("Horror");
        book.setGenre(genre);
        Author author = new Author();
        author.setName("Stephen King");
        book.getAuthors().add(author);
    }

    @Test
    public void count() {
        assertEquals(0, bookRepository.count());
        bookRepository.insert(book);
        assertEquals(1, bookRepository.count());
    }

    @Test
    public void insert() {
        bookRepository.insert(book);
        List<Book> books = bookRepository.getAll();
        Book savedBook = books.stream().findFirst().get();
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void save() {
        bookRepository.save(book);
        List<Book> books = bookRepository.getAll();
        Book savedBook = books.stream().findFirst().get();
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void delete() {
        bookRepository.insert(book);
        bookRepository.delete(book.getId());
        assertEquals(0, bookRepository.count());
    }

    @Test
    public void getById() {
        bookRepository.insert(book);
        Book savedBook = bookRepository.getById(book.getId());
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void getByName() {
        bookRepository.insert(book);
        Book savedBook = bookRepository.getByName("Shining");
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void getAll() {
        bookRepository.insert(book);
        List<Book> books = bookRepository.getAll();
        assertEquals(1, books.size());
        assertEquals(book.getName(), books.stream().findFirst().get().getName());
    }
}