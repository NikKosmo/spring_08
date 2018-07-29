package ru.otus.spring_06;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring_06.dao.BookDao;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        BookDao bookDao = context.getBean(BookDao.class);
        System.out.println(bookDao.getById(1));

        Console.main(args);
    }
}
