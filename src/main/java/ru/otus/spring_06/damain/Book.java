package ru.otus.spring_06.damain;

import java.util.List;

public class Book {

    private int id;
    private String name;
    private List<String> authors;
    private String genre;

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

    public List<String> getAuthors() {
        return authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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
