package ru.otus.spring_06.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring_06.damain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Collections.emptyMap(), Integer.class);
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public Book getById(int id) {
        return jdbc.queryForObject("select b.name, b.id, g.name as genre " +
                        "from books  b join genres g on b.genre_id = g.id and b.id = :id",
                Collections.singletonMap("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book result = new Book();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("NAME"));
            result.setGenre(resultSet.getString("GENRE"));
            return result;
        }
    }
}
