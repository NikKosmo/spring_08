package ru.otus.spring_07.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring_07.damain.Book;
import ru.otus.spring_07.damain.Comment;
import ru.otus.spring_07.repository.BookRepository;

import java.util.List;

@ShellComponent
public class ShellBookService implements BookService {

    private BookRepository repository;

    public ShellBookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    @ShellMethod("create")
    public void create(String name) {
        Book book = new Book();
        book.setName(name);
        repository.insert(book);
        System.out.println(book.getId());
    }

    @Override
    @ShellMethod("geById")
    public Book getById(@ShellOption int id) {
        System.out.println(id);
        Book result = repository.getById(id);
        System.out.println(result);
        return result;
    }

    @Override
    @ShellMethod("delete")
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    @ShellMethod("getAll")
    public List<Book> getAll() {
        List<Book> result = repository.getAll();
        System.out.println(result);
        return result;
    }

    @Override
    @ShellMethod("writeComment")
    public void writeComment(int bookId, String comment) {
        Book book = repository.getById(bookId);
        Comment e = new Comment();
        e.setText(comment);
        book.getComments().add(e);
        System.out.println(book);
        System.out.println(comment);
    }
}
