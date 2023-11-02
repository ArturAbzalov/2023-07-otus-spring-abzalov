package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.dao.impl.GenreDaoImpl;
import month_2.domain.Genre;
import month_2.mapper.GenreMapper;
import month_2.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@JdbcTest
@Import({GenreDaoImpl.class, GenreServiceImpl.class, GenreMapper.class})
@Slf4j
public class GenreJDBCTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAllGenreTest() {
        List<Genre> genreList = genreDao.getAll();
        int genreListSize = genreList.size();
        Assertions.assertEquals(2, genreListSize);
    }

    @Test
    void create() {
        int beforeCreateListSize = genreDao.getAll().size();
        Assertions.assertEquals(2, beforeCreateListSize);
        Genre genre = Genre.builder().id(1L).name("Test").build();
        genreDao.create(genre);
        int afterCreateListSize = genreDao.getAll().size();
        Assertions.assertEquals(3, afterCreateListSize);
    }

    @Test
    void getById() {
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Genre genreFromDb = genreDao.getById(1L).orElseThrow();
        Assertions.assertEquals(genre.getName(), genreFromDb.getName());
    }
}
