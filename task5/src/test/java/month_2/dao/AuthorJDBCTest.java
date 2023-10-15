package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.dao.impl.AuthorDaoImpl;
import month_2.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@JdbcTest
@Import(AuthorDaoImpl.class)
@Slf4j
public class AuthorJDBCTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getAllAuthorTest() {
        List<Author> authorList = authorDao.getAllAuthors();
        log.info(authorList.toString());
        Author author = Author.builder().id(1).firstName("Lev").lastName("Tolstoy").build();
        int authorListSize = authorList.size();
        log.info("Author list size: {} ", authorListSize);
        Assertions.assertEquals(4, authorListSize);
        boolean ifContains = authorList.contains(author);
        log.info("Author contains list: {}", ifContains);
        Assertions.assertTrue(ifContains);
    }
}
