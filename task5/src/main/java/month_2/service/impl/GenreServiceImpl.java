package month_2.service.impl;

import month_2.dao.GenreDao;
import month_2.domain.Genre;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre create(String name) {
        return genreDao.create(name);
    }
}
