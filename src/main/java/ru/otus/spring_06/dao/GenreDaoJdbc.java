package ru.otus.spring_06.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring_06.damain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre getById(int id) {
        try {
            return jdbc.queryForObject("select * from genres where id = :id",
                    Collections.singletonMap("id", id), new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre result = new Genre();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("NAME"));
            return result;
        }
    }
}
