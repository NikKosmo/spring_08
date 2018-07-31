package ru.otus.spring_06.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring_06.damain.Author;
import ru.otus.spring_06.damain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author getById(int id) {
        return jdbc.queryForObject("select * from authors where id = :id",
                Collections.singletonMap("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> findBookAuthors(int bookId) {
        List<Map<String, Object>> authors = jdbc.queryForList("select a.id, a.name from authors a" +
                        " join books_authors b on a.id = b.author_id and b.book_id = :bookId",
                Collections.singletonMap("bookId", bookId));
        return authors.stream()
                .map(this::mapRow)
                .collect(Collectors.toList());
    }

    private class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Author result = new Author();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
            return result;
        }
    }

    private Author mapRow(Map<String, Object> fieldMap) {
        Author result = new Author();
        result.setId((Integer) fieldMap.get("id"));
        result.setName((String) fieldMap.get("name"));
        return result;
    }
}
