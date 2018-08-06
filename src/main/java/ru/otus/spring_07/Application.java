package ru.otus.spring_07;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring_07.damain.Author;
import ru.otus.spring_07.damain.Book;
import ru.otus.spring_07.damain.Genre;
import ru.otus.spring_07.repository.BookRepository;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        BookRepository bookDao = context.getBean(BookRepository.class);
        Book book = new Book();
        book.setName("new name");
        Genre genre = new Genre();
        genre.setName("horror");
        book.setGenre(genre);
        Author author = new Author();
        author.setName("Uncle Bob");
        book.getAuthors().add(author);
        bookDao.insert(book);
        System.out.println(bookDao.count());
        System.out.println(bookDao.getByName("new name"));

        Console.main(args);
    }
}
