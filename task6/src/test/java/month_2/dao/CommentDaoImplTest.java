package month_2.dao;

import month_2.dao.impl.CommentDaoImpl;
import month_2.domain.Book;
import month_2.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoImplTest {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;


    @Test
    void getListByBookId() {
        Assertions.assertEquals(3, commentDao.getListByBookId(1L).size());
    }

    @Test
    void getById() {
        Comment comment = commentDao.getById(1L).orElseThrow();
        Assertions.assertEquals("excellent", comment.getMessage());
    }

    @Test
    void create() {
        Assertions.assertEquals(3, commentDao.getListByBookId(1L).size());
        Book book = Book.builder().id(1L).build();
        Comment comment = Comment.builder().message("perfecto").book(book).build();
        commentDao.create(comment);
        Assertions.assertEquals(4, commentDao.getListByBookId(1L).size());
    }

    @Test
    void update() {
        Comment comment = em.find(Comment.class,1L);
        em.detach(comment);
        comment.setMessage("it's fine");
        commentDao.update(comment);
        Comment commentFromDb = commentDao.getById(1L).orElseThrow();
        Assertions.assertEquals(comment.getMessage(), commentFromDb.getMessage());
    }

    @Test
    void deleteById() {
        Assertions.assertEquals(3, commentDao.getListByBookId(1L).size());
        commentDao.deleteById(1L);
        Assertions.assertEquals(2, commentDao.getListByBookId(1L).size());
    }

}
