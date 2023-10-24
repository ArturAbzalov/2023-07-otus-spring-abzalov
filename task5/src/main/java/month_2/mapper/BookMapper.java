package month_2.mapper;


import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import month_2.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book toEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .authorId(bookDto.getAuthorId())
                .genreId(bookDto.getGenreId())
                .build();
    }

    public BookDto toDto(Book book, Author author, Genre genre) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genreId(book.getGenreId())
                .authorId(book.getAuthorId())
                .authorFirstName(author.getFirstName())
                .authorLastName(author.getLastName())
                .genreName(genre.getName())
                .build();
    }

    public List<BookDto> toListDto(List<Book> bookList, List<Genre> genreList, List<Author> authorList) {
        Map<Long, Genre> genreMap = genreList.stream()
                .collect(Collectors.toMap(Genre::getId, x -> x, (x1, x2) -> x1));
        Map<Long, Author> authorMap = authorList.stream()
                .collect(Collectors.toMap(Author::getId, x -> x, (x1, x2) -> x1));
        return bookList.stream().map(book ->
                BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .genreName(genreMap.get(book.getGenreId()).getName())
                        .genreId(book.getGenreId())
                        .authorLastName(authorMap.get(book.getAuthorId()).getLastName())
                        .authorFirstName(authorMap.get(book.getAuthorId()).getFirstName())
                        .authorId(book.getAuthorId())
                .build()).toList();
    }
}
