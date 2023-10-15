package month_2.dao;

import month_2.dao.impl.BookDaoImpl;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@JdbcTest
@Import(BookDaoImpl.class)
public class BookJDBCTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void createBook() {
        Author author = Author.builder().firstName("TestName").lastName("TestLastName").build();
        Genre genre = Genre.builder().name("TestGenreName").build();
        Book book = Book.builder().author(author).genre(genre).name("TestBook").build();
        Assertions.assertEquals(3, bookDao.getAllBooks().size());
        bookDao.createBook(book);
        Assertions.assertEquals(4, bookDao.getAllBooks().size());
    }

    @Test
    void deleteBookById() {
        Assertions.assertEquals(3, bookDao.getAllBooks().size());
        bookDao.deleteBookById(5);
        Assertions.assertEquals(3, bookDao.getAllBooks().size());
        bookDao.deleteBookById(1);
        Assertions.assertEquals(2, bookDao.getAllBooks().size());
    }

    @Test
    void getAllBooks() {
        List<Book> bookList = bookDao.getAllBooks();
        Assertions.assertEquals(3, bookList.size());
        Author author = Author.builder().id(1).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(1).name("roman").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1).build();
        Assertions.assertTrue(bookList.contains(book));
    }

    @Test
    void updateBook() {
        Author author = Author.builder().id(1).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(2).name("fantasy").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1).build();
        bookDao.updateBook(book);
        List<Book> bookList = bookDao.getAllBooks();
        Assertions.assertTrue(bookList.contains(book));
    }
}