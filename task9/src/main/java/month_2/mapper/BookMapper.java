package month_2.mapper;


import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.book.BookCreateDto;
import month_2.dto.book.BookDto;
import month_2.dto.book.BookUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    @Autowired
    public BookMapper(AuthorMapper authorMapper, GenreMapper genreMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }


    public Book toEntity(BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .author(Author.builder().id(bookCreateDto.getAuthorId()).build())
                .genre(Genre.builder().id(bookCreateDto.getGenreId()).build())
                .build();
    }

    public Book toEntity(BookUpdateDto bookUpdateDto, Author author, Genre genre) {
        return Book.builder()
                .id(bookUpdateDto.getId())
                .name(bookUpdateDto.getName())
                .author(author)
                .genre(genre)
                .build();
    }

    public BookUpdateDto toUpdateDto(Book book) {
        return BookUpdateDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genreId(book.getGenre().getId())
                .authorId(book.getAuthor().getId())
                .build();
    }

    public BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genreDto(genreMapper.toDto(book.getGenre()))
                .authorDto(authorMapper.toDto(book.getAuthor()))
                .build();
    }

    public List<BookDto> toListDto(List<Book> bookList) {
        return bookList.stream().map(this::toDto).toList();
    }
}
