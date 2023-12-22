package month_2.service.impl;

import month_2.dao.GenreRepository;
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

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAll() {
        return genreMapper.toListDto(genreRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto getById(String id) {
        Genre genre =  genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %s not found", id)));
        return genreMapper.toDto(genre);
    }

    @Override
    @Transactional
    public GenreDto create(GenreDto genreDto) {
        Genre genre = genreRepository.save(genreMapper.toEntity(genreDto));
        return genreMapper.toDto(genre);
    }
}
