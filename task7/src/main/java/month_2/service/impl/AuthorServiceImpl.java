package month_2.service.impl;

import month_2.dao.AuthorRepository;
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

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAll() {
        return authorMapper.toListDto(authorRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getById(Long id) {
        Author author =  authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found", id)));
        return authorMapper.toDto(author);
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        Author author = authorRepository.save(authorMapper.toEntity(authorDto));
        return authorMapper.toDto(author);
    }
}
