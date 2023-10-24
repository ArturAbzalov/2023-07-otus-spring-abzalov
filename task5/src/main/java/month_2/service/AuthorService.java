package month_2.service;

import month_2.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author getById(Long id);

    Author create(String firstName, String lastName);
}
