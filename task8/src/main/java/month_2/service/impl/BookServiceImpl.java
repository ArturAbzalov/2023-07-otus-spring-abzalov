package month_2.service.impl;

import month_2.dao.AuthorRepository;
import month_2.dao.BookRepository;
import month_2.dao.GenreRepository;
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
        Author author = authorRepository.findById(bookDto.getAuthorDto().getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %s not found",
                        bookDto.getAuthorDto().getAuthorId())));
        Genre genre = genreRepository.findById(bookDto.getGenreDto().getGenreId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %s not found",
                        bookDto.getGenreDto().getGenreId())));
        Book book = bookMapper.toEntity(bookDto, genre, author);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException(String.format("Book with id: %s not found", id));
        }
        bookRepository.cascadeDeleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookMapper.toListDto(bookList);
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %s not found", bookDto.getId())));
        Author author = authorRepository.findById(bookDto.getAuthorDto().getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %s not found",
                        bookDto.getAuthorDto().getAuthorId())));
        Genre genre = genreRepository.findById(bookDto.getGenreDto().getGenreId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id: %s not found",
                        bookDto.getGenreDto().getGenreId())));
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto, genre, author)));
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id: %s not found", id)));
        return bookMapper.toDto(book);
    }
}
