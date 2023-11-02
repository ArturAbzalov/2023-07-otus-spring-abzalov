package month_2.dao.impl;

import lombok.extern.slf4j.Slf4j;
import month_2.dao.GenreDao;
import month_2.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, genrename from genres", (rs, rowNum) -> Genre.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("genrename"))
                .build());
    }

    @Override
    public Genre create(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genres(genrename) values (:genreName)"
                , new MapSqlParameterSource(Map.of("genreName", genre.getName())), keyHolder);
        return Genre.builder()
                .id(Objects.requireNonNull(keyHolder.getKey()).longValue())
                .name(genre.getName())
                .build();
    }

    @Override
    public Optional<Genre> getById(Long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select id, genrename " +
                            "from genres where id=:id",
                    Map.of("id", id), (rs, rowNum) ->
                            Genre.builder()
                                    .id(rs.getLong("id"))
                                    .name(rs.getString("genrename"))
                                    .build()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
