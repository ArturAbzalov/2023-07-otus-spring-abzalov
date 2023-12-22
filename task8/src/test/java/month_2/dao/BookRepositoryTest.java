package month_2.dao;

import month_2.domain.Book;
import month_2.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void cascadeDelete() {
        List<Book> bookList = bookRepository.findAll();
        bookRepository.cascadeDeleteById(bookList.get(0).getId());
        List<Comment> commentsListAfterDelete = commentRepository.findAll();
        List<Book> bookListAfterDelete = bookRepository.findAll();
        Assertions.assertEquals(4, commentsListAfterDelete.size());
        Assertions.assertEquals(2, bookListAfterDelete.size());
    }
}