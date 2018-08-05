package ru.otus.spring_06.dao;

import ru.otus.spring_06.damain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(int id);

    List<Author> findBookAuthors(int bookId);
}
