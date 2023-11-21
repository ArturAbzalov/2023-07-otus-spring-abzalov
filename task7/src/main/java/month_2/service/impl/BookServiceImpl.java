package month_2.service.impl;

import month_2.dao.AuthorDao;
import month_2.dao.BookDao;
import month_2.dao.GenreDao;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.BookDto;
import month_2.exception.NotFoundException;
import month_2.mapper.BookMapper;
import month_2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao,
                           BookMapper bookMapper) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        Author author = authorDao.findById(bookDto.getAuthorDto().getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found",
                        bookDto.getAuthorDto().getAuthorId())));
        Genre genre = genreDao.findById(bookDto.getGenreDto().getGenreId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found",
                        bookDto.getGenreDto().getGenreId())));
        Book book = bookMapper.toEntity(bookDto, genre, author);
        return bookMapper.toDto(bookDao.save(book));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!bookDao.existsById(id)) {
            throw new NotFoundException(String.format("Book with id: %d not found", id));
        }
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<BookDto> getAll() {
        List<Book> bookList = bookDao.findAll();
        return bookMapper.toListDto(bookList);
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        Author author = authorDao.findById(bookDto.getAuthorDto().getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found",
                        bookDto.getAuthorDto().getAuthorId())));
        Genre genre = genreDao.findById(bookDto.getGenreDto().getGenreId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found",
                        bookDto.getGenreDto().getGenreId())));
        bookDao.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %d not found", bookDto.getId())));
        return bookMapper.toDto(bookDao.save(bookMapper.toEntity(bookDto, genre, author)));
    }

    @Override
    @Transactional
    public BookDto getById(Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %d not found", id)));
        return bookMapper.toDto(book);
    }
}
