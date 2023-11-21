package month_2.service.impl;

import month_2.dao.GenreDao;
import month_2.domain.Genre;
import month_2.dto.GenreDto;
import month_2.exception.NotFoundException;
import month_2.mapper.GenreMapper;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<GenreDto> getAll() {
        return genreMapper.toListDto(genreDao.findAll());
    }

    @Override
    @Transactional
    public GenreDto getById(Long id) {
        Genre genre =  genreDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found", id)));
        return genreMapper.toDto(genre);
    }

    @Override
    @Transactional
    public GenreDto create(GenreDto genreDto) {
        Genre genre = genreDao.save(genreMapper.toEntity(genreDto));
        return genreMapper.toDto(genre);
    }
}
