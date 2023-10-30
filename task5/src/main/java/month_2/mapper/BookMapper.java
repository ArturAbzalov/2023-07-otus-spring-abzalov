package month_2.mapper;


import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.BookDto;
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


    public Book toEntity(BookDto bookDto, Genre genre, Author author) {
        return Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .genre(genre)
                .author(author)
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
        return bookList.stream().map(book ->
                BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .authorDto(authorMapper.toDto(book.getAuthor()))
                        .genreDto(genreMapper.toDto(book.getGenre()))
                        .build())
                .toList();
    }
}
