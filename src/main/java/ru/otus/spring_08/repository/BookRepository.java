package ru.otus.spring_08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring_08.damain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findByName(String name);
}
