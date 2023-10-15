package month_2.dao.impl;

import month_2.dao.GenreDao;
import month_2.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Genre> getAllGenre() {
        return jdbc.query("select id, genrename from genres", (rs, rowNum) -> Genre.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("genrename"))
                .build());
    }
}
