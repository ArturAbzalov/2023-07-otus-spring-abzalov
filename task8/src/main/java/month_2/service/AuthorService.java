package month_2.service;

import month_2.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto getById(String id);

    AuthorDto create(AuthorDto authorDto);
}
