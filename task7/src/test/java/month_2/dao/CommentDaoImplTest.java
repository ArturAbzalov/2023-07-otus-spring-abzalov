package month_2.dao;

import month_2.domain.Book;
import month_2.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CommentDaoImplTest {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;


    @Test
    void getAllByBookId() {
        Assertions.assertEquals(3, commentDao.getAllByBookId(1L).size());
    }

    @Test
    void getById() {
        Comment comment = commentDao.findById(1L).orElseThrow();
        Assertions.assertEquals("excellent", comment.getMessage());
    }

    @Test
    void create() {
        Assertions.assertEquals(3, commentDao.getAllByBookId(1L).size());
        Book book = Book.builder().id(1L).build();
        Comment comment = Comment.builder().message("perfecto").book(book).build();
        commentDao.save(comment);
        Assertions.assertEquals(4, commentDao.getAllByBookId(1L).size());
    }

    @Test
    void update() {
        Comment comment = em.find(Comment.class,1L);
        em.detach(comment);
        comment.setMessage("it's fine");
        commentDao.save(comment);
        Comment commentFromDb = commentDao.findById(1L).orElseThrow();
        Assertions.assertEquals(comment.getMessage(), commentFromDb.getMessage());
    }

    @Test
    void deleteById() {
        Assertions.assertEquals(3, commentDao.getAllByBookId(1L).size());
        commentDao.deleteById(1L);
        Assertions.assertEquals(2, commentDao.getAllByBookId(1L).size());
    }

}
