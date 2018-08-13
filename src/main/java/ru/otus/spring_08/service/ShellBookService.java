package ru.otus.spring_08.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring_08.damain.Author;
import ru.otus.spring_08.damain.Book;
import ru.otus.spring_08.damain.Comment;
import ru.otus.spring_08.damain.Genre;
import ru.otus.spring_08.repository.BookRepository;

import java.util.Optional;

@ShellComponent
public class ShellBookService implements BookService {

    private BookRepository repository;

    public ShellBookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    @ShellMethod("create")
    public Book create(String name, String genreName, String authorName) {
        Book book = new Book();
        book.setName(name);
        Genre genre = new Genre();
        genre.setName(genreName);
        book.setGenre(genre);
        Author author = new Author();
        author.setName(authorName);
        book.getAuthors().add(author);
        return repository.save(book);
    }

    @Override
    @ShellMethod("geById")
    public Book getById(@ShellOption int bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    @ShellMethod("delete")
    public void delete(int bookId) {
        Optional<Book> book = repository.findById(bookId);
        book.ifPresent(book1 -> repository.delete(book1));
    }

    @Override
    @ShellMethod("getAll")
    public Iterable<Book> getAll() {
        return repository.findAll();
    }

    @Override
    @ShellMethod("writeComment")
    public void writeComment(int bookId, String commentText) {
        Optional<Book> bookOptional = repository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Comment comment = new Comment();
            comment.setText(commentText);
            book.getComments().add(comment);
            repository.save(book);
        }
    }
}
