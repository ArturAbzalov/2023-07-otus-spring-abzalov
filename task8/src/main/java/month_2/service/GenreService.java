package month_2.service;

import month_2.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();

    GenreDto getById(String id);

    GenreDto create(GenreDto name);
}
