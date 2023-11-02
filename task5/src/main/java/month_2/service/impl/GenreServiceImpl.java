package month_2.service.impl;

import month_2.dao.GenreDao;
import month_2.domain.Genre;
import month_2.dto.GenreDto;
import month_2.exception.NotFoundException;
import month_2.mapper.GenreMapper;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao, GenreMapper genreMapper) {
        this.genreDao = genreDao;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found", id)));
    }

    @Override
    public Genre create(GenreDto genreDto) {
        return genreDao.create(genreMapper.toEntity(genreDto));
    }
}
