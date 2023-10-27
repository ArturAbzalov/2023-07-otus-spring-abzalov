package month_2.dao;

import month_2.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();

    Author create(Author author);

    Author getById(Long id);
}
