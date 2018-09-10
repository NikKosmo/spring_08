package ru.otus.spring_08.service;

import ru.otus.spring_08.damain.Book;

import java.util.List;

public interface BookService {

    Book create(String name, String genreName, String authorName);

    Book getById(int id);

    void delete(int id);

    Iterable<Book> getAll();

    void writeComment(int bookId, String comment);
}
