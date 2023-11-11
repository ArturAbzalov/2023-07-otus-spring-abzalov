package month_2.dao;

import month_2.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author> getAll();

    Author create(Author author);

    Optional<Author> getById(Long id);
}
