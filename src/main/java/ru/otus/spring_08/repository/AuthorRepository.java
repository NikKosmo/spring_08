package ru.otus.spring_08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring_08.damain.Author;


public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findByName(String name);

}
