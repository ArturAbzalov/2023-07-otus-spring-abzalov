package month_2.dao;


import lombok.extern.slf4j.Slf4j;
import month_2.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@Slf4j
public class GenreDaoImplTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAllGenreTest() {
        List<Genre> genreList = genreDao.findAll();
        int genreListSize = genreList.size();
        Assertions.assertEquals(2, genreListSize);
    }

    @Test
    void create() {
        int beforeCreateListSize = genreDao.findAll().size();
        Assertions.assertEquals(2, beforeCreateListSize);
        Genre genre = Genre.builder().name("Test").build();
        genreDao.save(genre);
        int afterCreateListSize = genreDao.findAll().size();
        Assertions.assertEquals(3, afterCreateListSize);
    }

    @Test
    void getById() {
        Genre genre = Genre.builder().id(1L).name("roman").build();
        Genre genreFromDb = genreDao.findById(1L).orElseThrow();
        Assertions.assertEquals(genre.getName(), genreFromDb.getName());
    }
}
