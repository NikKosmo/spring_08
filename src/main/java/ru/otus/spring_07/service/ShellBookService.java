package ru.otus.spring_07.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring_07.damain.Author;
import ru.otus.spring_07.damain.Book;
import ru.otus.spring_07.damain.Comment;
import ru.otus.spring_07.repository.AuthorRepository;
import ru.otus.spring_07.repository.BookRepository;
import ru.otus.spring_07.repository.GenreRepository;

import java.util.List;

@ShellComponent
public class ShellBookService implements BookService {

    private BookRepository repository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    public ShellBookService(BookRepository repository,
                            GenreRepository genreRepository,
                            AuthorRepository authorRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @ShellMethod("create")
    public void create(String name, String genreName, String authorName) {
        Book book = new Book();
        book.setName(name);
        book.setGenre(genreRepository.getByName(genreName));
        Author author = authorRepository.getByName(authorName);
        if (author != null) {
            book.getAuthors().add(author);
        }
        repository.insert(book);
        System.out.println(book.getId());
    }

    @Override
    @ShellMethod("geById")
    public Book getById(@ShellOption int id) {
        return repository.getById(id);
    }

    @Override
    @ShellMethod("delete")
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    @ShellMethod("getAll")
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    @ShellMethod("writeComment")
    public void writeComment(int bookId, String commentText) {
        Book book = repository.getById(bookId);
        Comment comment = new Comment();
        comment.setText(commentText);
        book.getComments().add(comment);
        repository.save(book);
    }
}
