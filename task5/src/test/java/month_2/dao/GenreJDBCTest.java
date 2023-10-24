package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.dao.impl.GenreDaoImpl;
import month_2.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@JdbcTest
@Import(GenreDaoImpl.class)
@Slf4j
public class GenreJDBCTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAllGenreTest() {
        List<Genre> genreList = genreDao.getAll();
        log.info(genreList.toString());
        Genre genre = Genre.builder().id(1L).name("roman").build();
        int genreListSize = genreList.size();
        log.info("Genre list size: {} ", genreListSize);
        Assertions.assertEquals(2, genreListSize);
        boolean ifContains = genreList.contains(genre);
        log.info("Genre contains list: {}", ifContains);
        Assertions.assertTrue(ifContains);
    }

    @Test
    void create() {
        int beforeCreateListSize = genreDao.getAll().size();
        Assertions.assertEquals(2, beforeCreateListSize);
        genreDao.create("Test");
        genreDao.create("Test");
        int afterCreateListSize = genreDao.getAll().size();
        Assertions.assertEquals(3, afterCreateListSize);
    }

    @Test
    void getById() {
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Assertions.assertEquals(genre, genreDao.getById(1L));
    }
}
