package month_2.dao;

import month_2.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book create(Book book);

    void deleteById(long id);

    List<Book> getAll();

    Book update(Book book);

    Optional<Book> getById(long id);
}
