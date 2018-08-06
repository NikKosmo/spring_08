package ru.otus.spring_07.damain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade=CascadeType.ALL)
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
