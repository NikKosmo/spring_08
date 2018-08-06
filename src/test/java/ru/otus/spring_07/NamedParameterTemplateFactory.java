package ru.otus.spring_07;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class NamedParameterTemplateFactory {

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String schema, String dataFill) {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(schema)
                .addScript(dataFill)
                .build();
        return new NamedParameterJdbcTemplate(db);
    }
}
