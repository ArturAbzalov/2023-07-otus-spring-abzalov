package month_2.service;

import month_2.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(String name, String firstName, String lastName, String genreName);

    void deleteBookById(long id);

    List<Book> getAllBooks();

    void updateBook(String name, String firstName, String lastName, String genreName);
}
