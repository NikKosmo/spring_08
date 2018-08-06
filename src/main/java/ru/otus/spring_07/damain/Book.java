package ru.otus.spring_07.damain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
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
