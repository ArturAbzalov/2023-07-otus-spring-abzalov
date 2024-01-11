package month_2.service.impl;

import month_2.dao.AuthorRepository;
import month_2.dao.BookRepository;
import month_2.dao.GenreRepository;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.book.BookDto;
import month_2.dto.book.BookUpdateDto;
import month_2.exception.NotFoundException;
import month_2.mapper.BookMapper;
import month_2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorDto().getId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found",
                        bookDto.getAuthorDto().getId())));
        Genre genre = genreRepository.findById(bookDto.getGenreDto().getId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found",
                        bookDto.getGenreDto().getId())));
        Book book = bookMapper.toEntity(bookDto, genre, author);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookMapper.toListDto(bookList);
    }

    @Override
    @Transactional
    public BookDto update(BookUpdateDto bookUpdateDto) {
        bookRepository.findById(bookUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %d not found",
                        bookUpdateDto.getId())));
        Author author = authorRepository.findById(bookUpdateDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %d not found",
                        bookUpdateDto.getAuthorId())));
        Genre genre = genreRepository.findById(bookUpdateDto.getGenreId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %d not found",
                        bookUpdateDto.getGenreId())));
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookUpdateDto, author, genre)));
    }

    @Override
    @Transactional(readOnly = true)
    public BookUpdateDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %d not found", id)));
        return bookMapper.toUpdateDto(book);
    }
}
