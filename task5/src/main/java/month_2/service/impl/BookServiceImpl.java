package month_2.service.impl;

import month_2.dao.BookDao;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void createBook(String name, String firstName, String lastName, String genreName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        Genre genre = Genre.builder().name(genreName).build();
        Book book = Book.builder().name(name).genre(genre).author(author).build();
        bookDao.createBook(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void updateBook(String name, String firstName, String lastName, String genreName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        Genre genre = Genre.builder().name(genreName).build();
        Book book = Book.builder().name(name).genre(genre).author(author).build();
        bookDao.updateBook(book);
    }


}
