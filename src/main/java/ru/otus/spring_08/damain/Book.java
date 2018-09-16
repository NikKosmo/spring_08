package ru.otus.spring_08.damain;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.HashSet;
import java.util.Set;

@KeySpace("book")
public class Book {

    @Id
    private Integer id;
    private String name;

    private Set<Author> authors = new HashSet<>();

    private Genre genre;

    private Set<Comment> comments = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genre=" + genre +
                ", comments=" + comments +
                '}';
    }
}
