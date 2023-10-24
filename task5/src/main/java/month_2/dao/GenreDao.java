package month_2.dao;

import month_2.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    Genre create(String genreName);

    Genre getById(Long id);
}
