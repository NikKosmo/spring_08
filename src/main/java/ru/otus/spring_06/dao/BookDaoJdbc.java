package ru.otus.spring_06.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring_06.damain.Author;
import ru.otus.spring_06.damain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbc;
    private GenreDao genreDao;
    private AuthorDao authorDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, GenreDao genreDao, AuthorDao authorDao) {
        this.jdbc = jdbc;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Collections.emptyMap(), Integer.class);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", book.getId());
        parameterMap.put("name", book.getName());
        parameterMap.put("genre_id", book.getGenre().getId());
        jdbc.update("insert into books (id, `name`, genre_id) values (:id, :name, :genre_id)", parameterMap);

        List<Map<String, Object>> authorMap = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookId", book.getId());
            map.put("authorId", author.getId());
            authorMap.add(map);
        }
        jdbc.batchUpdate("insert into books_authors (`book_id`, `author_id`) values (:bookId, :authorId)", authorMap.toArray(new Map[authorMap.size()]));
    }

    @Override
    public void delete(int id) {
        Map<String, Integer> parameterMap = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", parameterMap);
        jdbc.update("delete from books_authors where book_id = :id", parameterMap);
    }

    @Override
    public Book getById(int id) {
        try {
            Map<String, Integer> parameterMap = Collections.singletonMap("id", id);
            Book book = jdbc.queryForObject("select * from books where id = :id",
                    parameterMap, new BookMapper());
            if (book != null) {
                book.getAuthors().addAll(authorDao.findBookAuthors(id));
            }
            return book;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", Collections.emptyMap(), new BookMapper());
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book result = new Book();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
            result.setGenre(genreDao.getById(resultSet.getInt("genre_id")));
            return result;

        }
    }
}
