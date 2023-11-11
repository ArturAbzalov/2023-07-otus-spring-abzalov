package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.dao.impl.AuthorDaoImpl;
import month_2.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(AuthorDaoImpl.class)
@Slf4j
public class AuthorJPaTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getAllAuthorTest() {
        List<Author> authorList = authorDao.getAll();
        int authorListSize = authorList.size();
        Assertions.assertEquals(4, authorListSize);
    }

    @Test
    void create() {
        int beforeCreateListSize = authorDao.getAll().size();
        Assertions.assertEquals(4, beforeCreateListSize);
        Author author = Author.builder().firstName("One").lastName("One").build();
        authorDao.create(author);
        int afterCreateListSize = authorDao.getAll().size();
        Assertions.assertEquals(5, afterCreateListSize);
    }

    @Test
    void getById() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Author authorFromDb = authorDao.getById(1L).orElseThrow();
        Assertions.assertEquals(author.getFirstName(), authorFromDb.getFirstName());
        Assertions.assertEquals(author.getLastName(), authorFromDb.getLastName());
    }

}
