package month_2.service;

import month_2.domain.Author;
import month_2.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author getById(Long id);

    Author create(AuthorDto authorDto);
}
