package month_2.dao;

import month_2.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getAllByBookId() {
        List<Book> bookList = bookRepository.findAll();
        Assertions.assertEquals(2, commentRepository.getAllByBookId(bookList.get(1).getId()).size());
    }

}
