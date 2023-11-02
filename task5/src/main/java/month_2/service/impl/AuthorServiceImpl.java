package month_2.service.impl;

import month_2.dao.AuthorDao;
import month_2.domain.Author;
import month_2.dto.AuthorDto;
import month_2.exception.NotFoundException;
import month_2.mapper.AuthorMapper;
import month_2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found", id)));
    }

    @Override
    public Author create(AuthorDto authorDto) {
        return authorDao.create(authorMapper.toEntity(authorDto));
    }
}
