package ru.otus.spring_06.dao;

import ru.otus.spring_06.damain.Book;

import java.util.List;

public interface BookDao {

    int count();

    void insert(Book book);

    Book getById(int id);

    List<Book> getAll();
}
