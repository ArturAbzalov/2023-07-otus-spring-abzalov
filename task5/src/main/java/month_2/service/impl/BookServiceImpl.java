package month_2.service.impl;

import month_2.dao.BookDao;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.BookDto;
import month_2.mapper.BookMapper;
import month_2.service.AuthorService;
import month_2.service.BookService;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Author author = authorService.create(bookDto.getAuthorFirstName(), bookDto.getAuthorLastName());
        Genre genre = genreService.create(bookDto.getGenreName());
        bookDto.setAuthorId(author.getId());
        bookDto.setGenreId(genre.getId());
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookDao.create(book), author, genre);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = bookDao.getAll();
        List<Genre> genreList = genreService.getAll();
        List<Author> authorList = authorService.getAll();
        return bookMapper.toListDto(bookList, genreList, authorList);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Author author = authorService.create(bookDto.getAuthorFirstName(), bookDto.getAuthorLastName());
        Genre genre = genreService.create(bookDto.getGenreName());
        bookDto.setAuthorId(author.getId());
        bookDto.setGenreId(genre.getId());
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookDao.update(book), author, genre);
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookDao.getById(id);
        Author author = authorService.getById(book.getAuthorId());
        Genre genre = genreService.getById(book.getGenreId());
        return bookMapper.toDto(book, author, genre);
    }
}
