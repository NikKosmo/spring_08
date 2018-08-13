package ru.otus.spring_08.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring_08.damain.Author;
import ru.otus.spring_08.damain.Book;
import ru.otus.spring_08.damain.Genre;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@Transactional
@ComponentScan("ru.otus.spring_08")
public class BookRepositoryTest {

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

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    @Test
    public void count() {
        assertEquals(0, bookRepository.count());
        bookRepository.save(book);
        assertEquals(1, bookRepository.count());
    }

    @Test
    public void insert() {
        bookRepository.save(book);
        List<Book> books = transformToList(bookRepository.findAll());
        Book savedBook = books.stream().findFirst().get();
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void save() {
        bookRepository.save(book);
        List<Book> books = transformToList(bookRepository.findAll());
        Book savedBook = books.stream().findFirst().get();
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void delete() {
        bookRepository.save(book);
        bookRepository.delete(book);
        assertEquals(0, bookRepository.count());
    }

    @Test
    public void getById() {
        bookRepository.save(book);
        Book savedBook = bookRepository.findById(book.getId()).get();
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void getByName() {
        bookRepository.save(book);
        Book savedBook = bookRepository.findByName("Shining");
        assertEquals(book.getName(), savedBook.getName());
    }

    @Test
    public void getAll() {
        bookRepository.save(book);
        List<Book> books = transformToList(bookRepository.findAll());
        assertEquals(1, books.size());
        assertEquals(book.getName(), books.stream().findFirst().get().getName());
    }

    private List<Book> transformToList(Iterable<Book> books) {
        return StreamSupport.stream(books.spliterator(), false)
                .collect(Collectors.toList());
    }
}