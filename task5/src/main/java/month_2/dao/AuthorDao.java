package month_2.dao;

import month_2.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();

    Author create(String name, String lastName);

    Author getById(Long id);
}
