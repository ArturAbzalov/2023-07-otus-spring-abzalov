package month_2.dao.impl;

import lombok.extern.slf4j.Slf4j;
import month_2.dao.AuthorDao;
import month_2.domain.Author;
import month_2.exception.IdNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, firstname, lastname from authors", (rs, rowNum) -> Author.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("firstname"))
                .lastName(rs.getString("lastname"))
                .build());
    }

    @Override
    public Author create(String firstName, String lastName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Long id = null;
        jdbc.update("merge into authors(firstname,lastname) key (firstname,lastname) values (:firstName,:lastName)"
                , new MapSqlParameterSource(Map.of("firstName", firstName, "lastName", lastName)), keyHolder);
        try {
            id = Optional.ofNullable(keyHolder.getKey()).orElseThrow(IdNullException::new).longValue();
        } catch (IdNullException e) {
            log.debug("Ошибка при получении id автора, id = null");
        }
        return Author.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    @Override
    public Author getById(Long id) {
        return jdbc.queryForObject("select id, firstname, lastname " +
                        "from authors where id=:id",
                Map.of("id", id), (rs, rowNum) ->
                        Author.builder()
                                .id(rs.getLong("id"))
                                .lastName(rs.getString("lastname"))
                                .firstName(rs.getString("firstname"))
                                .build());
    }


}
