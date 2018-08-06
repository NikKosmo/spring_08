package ru.otus.spring_07.service;

import ru.otus.spring_07.damain.Book;

import java.util.List;

public interface BookService {

    void create(String name);

    Book getById(int id);

    void delete(int id);

    List<Book> getAll();

    void writeComment(int bookId, String comment);
}
