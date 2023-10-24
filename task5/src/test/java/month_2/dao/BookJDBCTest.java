package month_2.dao;

import month_2.dao.impl.BookDaoImpl;
import month_2.domain.Book;
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
        Book book = Book.builder().authorId(1L).genreId(1L).name("TestBook").build();
        Assertions.assertEquals(3, bookDao.getAll().size());
        bookDao.create(book);
        System.out.println(bookDao.getAll());
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
        List<Book> bookList = bookDao.getAll();
        Assertions.assertEquals(3, bookList.size());
        Book book = Book.builder().name("War&peace").authorId(1L).genreId(1L).id(1L).build();
        Assertions.assertTrue(bookList.contains(book));
    }

    @Test
    void updateBook() {
        List<Book> bookListBeforeUpdate = bookDao.getAll();
        Assertions.assertEquals(3, bookListBeforeUpdate.size());
        Book book = Book.builder().name("War&peace").authorId(1L).genreId(2L).id(1L).build();
        bookDao.update(book);
        List<Book> bookListAfterUpdate = bookDao.getAll();
        Assertions.assertEquals(3, bookListBeforeUpdate.size());
        Assertions.assertTrue(bookListAfterUpdate.contains(book));
    }

    @Test
    void getById() {
        Book book = Book.builder().name("War&peace").authorId(1L).genreId(1L).id(1L).build();
        Assertions.assertEquals(book, bookDao.getById(1));
    }
}