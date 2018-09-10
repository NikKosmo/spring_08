package ru.otus.spring_08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring_08.damain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

    Genre findByName(String name);
}
