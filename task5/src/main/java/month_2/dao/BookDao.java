package month_2.dao;

import month_2.domain.Book;

import java.util.List;

public interface BookDao {
    Book create(Book book);

    void deleteById(long id);

    List<Book> getAll();

    Book update(Book book);

    Book getById(long id);
}
