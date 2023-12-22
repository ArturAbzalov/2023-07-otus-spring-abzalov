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
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager tm;

    @Test
    void getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        Assertions.assertEquals(3, bookList.size());
    }

    @Test
    void findById() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Book book = Book.builder().name("War&peace").author(author).genre(genre).build();
        tm.persistAndFlush(book);
        tm.detach(book);
        Book bookFromDb = bookRepository.findById(4L).orElseThrow();
        Assertions.assertEquals(book.getName(), bookFromDb.getName());
        Assertions.assertEquals(book.getGenre().getId(), bookFromDb.getGenre().getId());
        Assertions.assertEquals(book.getGenre().getName(), bookFromDb.getGenre().getName());
        Assertions.assertEquals(book.getAuthor().getFirstName(), bookFromDb.getAuthor().getFirstName());
        Assertions.assertEquals(book.getAuthor().getLastName(), bookFromDb.getAuthor().getLastName());
        Assertions.assertEquals(book.getAuthor().getId(), bookFromDb.getAuthor().getId());
    }
}