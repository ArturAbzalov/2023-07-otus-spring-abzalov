package month_2.dao;

import month_2.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    Genre create(Genre genre);

    Genre getById(Long id);
}
