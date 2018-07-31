package ru.otus.spring_06.damain;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int id;
    private String name;
    private List<Author> authors = new ArrayList<>();
    private Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", genre='" + genre + '\'' +
                '}';
    }
}
