package month_2.service.impl;

import month_2.dao.AuthorDao;
import month_2.domain.Author;
import month_2.dto.AuthorDto;
import month_2.exception.NotFoundException;
import month_2.mapper.AuthorMapper;
import month_2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, AuthorMapper authorMapper) {
        this.authorDao = authorDao;
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional
    public List<AuthorDto> getAll() {
        return authorMapper.toListDto(authorDao.findAll());
    }

    @Override
    @Transactional
    public AuthorDto getById(Long id) {
        Author author =  authorDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found", id)));
        return authorMapper.toDto(author);
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        Author author = authorDao.save(authorMapper.toEntity(authorDto));
        return authorMapper.toDto(author);
    }
}
