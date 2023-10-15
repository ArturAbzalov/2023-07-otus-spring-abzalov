package month_2.dao;

import month_2.domain.Book;

import java.util.List;

public interface BookDao {
    void createBook(Book book);

    void deleteBookById(long id);

    List<Book> getAllBooks();

    void updateBook(Book book);
}
