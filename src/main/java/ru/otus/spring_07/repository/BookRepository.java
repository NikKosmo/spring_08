package ru.otus.spring_07.repository;

import ru.otus.spring_07.damain.Book;

import java.util.List;

public interface BookRepository {

    int count();

    void insert(Book book);

    void save(Book book);

    void delete(int id);

    Book getById(int id);

    Book getByName(String name);

    List<Book> getAll();
}
