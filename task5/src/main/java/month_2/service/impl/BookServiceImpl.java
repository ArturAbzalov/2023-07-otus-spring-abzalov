package month_2.service.impl;

import month_2.dao.BookDao;
import month_2.domain.Book;
import month_2.dto.BookDto;
import month_2.exception.BookNotFoundException;
import month_2.mapper.BookMapper;
import month_2.service.AuthorService;
import month_2.service.BookService;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService,
                           BookMapper bookMapper) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        checkExists(bookDto);
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookDao.create(book));
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = bookDao.getAll();
        return bookMapper.toListDto(bookList);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        checkExists(bookDto);
        try {
            bookDao.getById(bookDto.getId());
            return bookMapper.toDto(bookDao.update(bookMapper.toEntity(bookDto)));
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(String.format("Book with id: %d not found", bookDto.getId()));
        }
    }

    @Override
    public BookDto getById(Long id) {
        try {
            Book book = bookDao.getById(id);
            return bookMapper.toDto(book);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(String.format("Book with id: %d not found", id));
        }
    }

    private void checkExists(BookDto bookDto) {
        authorService.getById(bookDto.getAuthorDto().getAuthorId());
        genreService.getById(bookDto.getGenreDto().getGenreId());
    }
}
