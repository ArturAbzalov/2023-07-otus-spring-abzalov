package month_2.service;

import month_2.domain.Genre;
import month_2.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getById(Long id);

    Genre create(GenreDto name);
}
