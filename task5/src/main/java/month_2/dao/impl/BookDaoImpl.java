package month_2.dao.impl;

import lombok.extern.slf4j.Slf4j;
import month_2.dao.BookDao;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books(bookname, author_id, genre_id) values(:bookname,:author_id,:genre_id)",
                new MapSqlParameterSource(Map.of("bookname", book.getName(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId())), keyHolder);
        book.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return book;
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where book_id=:bookId",
                Map.of("bookId", id));
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select book_id, bookname,firstname,lastname,genrename,author_id,genre_id from books " +
                        "left join authors on books.author_id=authors.id " +
                        "left join genres on books.genre_id=genres.id", new BookMapper());
    }

    @Override
    public Book update(Book book) {
        jdbc.update("update books set bookname=:bookname,author_id=:author_id,genre_id=:genre_id " +
                "where book_id=:book_id", new MapSqlParameterSource(
                Map.of("book_id", book.getId(), "bookname", book.getName(),
                        "genre_id", book.getGenre().getId(), "author_id", book.getAuthor().getId())));
        return book;
    }

    @Override
    public Optional<Book> getById(long id) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject("select book_id, bookname,firstname,lastname,genrename,author_id,genre_id "
                                    + "from books join authors on books.author_id=authors.id " +
                            "join genres on books.genre_id=genres.id where book_id=:book_id",
                    Map.of("book_id", id), new BookMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = Author.builder()
                    .id(rs.getLong("author_id"))
                    .firstName(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .build();
            Genre genre = Genre.builder()
                    .id(rs.getLong("genre_id"))
                    .name(rs.getString("genrename"))
                    .build();
            return Book.builder()
                    .id(rs.getLong("book_id"))
                    .name(rs.getString("bookname"))
                    .author(author)
                    .genre(genre)
                    .build();
        }
    }

}
