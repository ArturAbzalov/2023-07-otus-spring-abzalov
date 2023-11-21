package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@Slf4j
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getAllAuthorTest() {
        List<Author> authorList = authorDao.findAll();
        int authorListSize = authorList.size();
        Assertions.assertEquals(4, authorListSize);
    }

    @Test
    void create() {
        int beforeCreateListSize = authorDao.findAll().size();
        Assertions.assertEquals(4, beforeCreateListSize);
        Author author = Author.builder().firstName("One").lastName("One").build();
        authorDao.save(author);
        int afterCreateListSize = authorDao.findAll().size();
        Assertions.assertEquals(5, afterCreateListSize);
    }

    @Test
    void getById() {
        Author author = Author.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        Author authorFromDb = authorDao.findById(1L).orElseThrow();
        Assertions.assertEquals(author.getFirstName(), authorFromDb.getFirstName());
        Assertions.assertEquals(author.getLastName(), authorFromDb.getLastName());
    }

}
