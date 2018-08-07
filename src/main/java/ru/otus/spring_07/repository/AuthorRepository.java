package ru.otus.spring_07.repository;

import ru.otus.spring_07.damain.Author;

public interface AuthorRepository {

    Author getById(int id);

    Author getByName(String name);

}
