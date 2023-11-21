package month_2.dao;

import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void createBook() {
        Author author = Author.builder().id(1L).build();
        Genre genre = Genre.builder().id(1L).build();
        Book book = Book.builder().author(author).genre(genre).name("TestBook").build();
        Assertions.assertEquals(3, bookDao.findAll().size());
        bookDao.save(book);
        Assertions.assertEquals(4, bookDao.findAll().size());
    }

    @Test
    void deleteBookById() {
        Assertions.assertEquals(3, bookDao.findAll().size());
        Book book = em.find(Book.class,1L);
        bookDao.deleteById(book.getId());
        Assertions.assertEquals(2, bookDao.findAll().size());
    }

    @Test
    void getAllBooks() {
        List<Book> bookList = bookDao.findAll();
        Assertions.assertEquals(3, bookList.size());
    }

    @Test
    void updateBook() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(2L).name("fantasy").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1L).build();
        bookDao.save(book);
        Book bookFromDb = bookDao.findById(1L).orElseThrow();
        Assertions.assertEquals(book.getName(), bookFromDb.getName());
        Assertions.assertEquals(book.getGenre().getId(), bookFromDb.getGenre().getId());
        Assertions.assertEquals(book.getGenre().getName(), bookFromDb.getGenre().getName());
        Assertions.assertEquals(book.getAuthor().getFirstName(), bookFromDb.getAuthor().getFirstName());
        Assertions.assertEquals(book.getAuthor().getLastName(), bookFromDb.getAuthor().getLastName());
        Assertions.assertEquals(book.getAuthor().getId(), bookFromDb.getAuthor().getId());
    }

    @Test
    void getById() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).id(1L).build();
        Book bookFromDb = bookDao.findById(1L).orElseThrow();
        Assertions.assertEquals(book.getName(), bookFromDb.getName());
        Assertions.assertEquals(book.getGenre().getId(), bookFromDb.getGenre().getId());
        Assertions.assertEquals(book.getGenre().getName(), bookFromDb.getGenre().getName());
        Assertions.assertEquals(book.getAuthor().getFirstName(), bookFromDb.getAuthor().getFirstName());
        Assertions.assertEquals(book.getAuthor().getLastName(), bookFromDb.getAuthor().getLastName());
        Assertions.assertEquals(book.getAuthor().getId(), bookFromDb.getAuthor().getId());
    }
}