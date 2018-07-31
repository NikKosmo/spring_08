package ru.otus.spring_06;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring_06.damain.Book;
import ru.otus.spring_06.damain.Genre;
import ru.otus.spring_06.dao.AuthorDao;
import ru.otus.spring_06.dao.BookDao;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        BookDao bookDao = context.getBean(BookDao.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        Book book = new Book();
        book.setId(2);
        book.setName("new name");
        Genre genre = new Genre();
        genre.setId(1);
        book.setGenre(genre);
        book.getAuthors().add(authorDao.getById(1));
        bookDao.insert(book);
        System.out.println(bookDao.getById(2));

        Console.main(args);
    }
}
