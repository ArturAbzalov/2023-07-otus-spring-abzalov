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
        Author author = Author.builder().id(1L).build();
        Genre genre = Genre.builder().id(1L).build();
        Book book = Book.builder().author(author).genre(genre).name("TestBook").build();
        Assertions.assertEquals(3, bookDao.getAll().size());
        bookDao.create(book);
        Assertions.assertEquals(4, bookDao.getAll().size());
    }

    @Test
    void deleteBookById() {
        Assertions.assertEquals(3, bookDao.getAll().size());
        bookDao.deleteById(5);
        Assertions.assertEquals(3, bookDao.getAll().size());
        bookDao.deleteById(1);
        Assertions.assertEquals(2, bookDao.getAll().size());
    }

    @Test
    void getAllBooks() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(1L).name("roman").build();
        List<Book> bookList = bookDao.getAll();
        Assertions.assertEquals(3, bookList.size());
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1L).build();
        Assertions.assertTrue(bookList.contains(book));
    }

    @Test
    void updateBook() {
        List<Book> bookListBeforeUpdate = bookDao.getAll();
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(2L).name("fantasy").build();
        Assertions.assertEquals(3, bookListBeforeUpdate.size());
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1L).build();
        bookDao.update(book);
        List<Book> bookListAfterUpdate = bookDao.getAll();
        Assertions.assertEquals(3, bookListBeforeUpdate.size());
        Assertions.assertTrue(bookListAfterUpdate.contains(book));
    }

    @Test
    void getById() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1L).build();
        Assertions.assertEquals(book, bookDao.getById(1));
    }
}