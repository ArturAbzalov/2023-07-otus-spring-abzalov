package month_2.dao;

import month_2.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    List<Genre> getAll();

    Genre create(Genre genre);

    Optional<Genre> getById(Long id);
}
