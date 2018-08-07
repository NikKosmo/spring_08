package ru.otus.spring_07.repository;

import ru.otus.spring_07.damain.Genre;

public interface GenreRepository {

    Genre getById(int id);

    Genre getByName(String name);
}
