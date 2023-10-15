package month_2.dao.impl;

import month_2.dao.BookDao;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createBook(Book book) {
        Long authorId = jdbc.queryForObject(
                "select id from final table(insert into authors(firstname,lastname) values(:firstname,:lastname))",
                Map.of("firstname", book.getAuthor().getFirstName(), "lastname", book.getAuthor().getLastName()),
                Long.class);
        Long genreId = jdbc.queryForObject(
                "select id from final table(insert into genres(genrename) values(:genrename))",
                Map.of("genrename", book.getGenre().getName()), Long.class);
        jdbc.update("insert into books(bookname, author_id, genre_id) values(:bookname,:author_id,:genre_id)",
                Map.of("bookname", book.getName(), "author_id", authorId, "genre_id", genreId));
    }

    @Override
    public void deleteBookById(long id) {
        jdbc.update("delete from books where book_id=:bookId",
                Map.of("bookId", id));
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbc
                .query("select book_id, bookname,firstname,lastname,genrename,author_id,genre_id from books " +
                        "left join authors on books.author_id=authors.id " +
                        "left join genres on books.genre_id=genres.id", (rs, rowNum) -> {
                    Author author = Author.builder().id(rs.getLong("author_id"))
                            .firstName(rs.getString("firstname"))
                            .lastName(rs.getString("lastname"))
                            .build();
                    Genre genre = Genre.builder().id(rs.getLong("genre_id"))
                            .name(rs.getString("genrename")).build();
                    return Book.builder()
                            .id(rs.getLong("book_id"))
                            .name(rs.getString("bookname"))
                            .author(author)
                            .genre(genre)
                            .build();
                });
    }

    @Override
    public void updateBook(Book book) {
        jdbc.update("update books set bookname=:bookname,author_id=:authorId,genre_id=:genreId where book_id=:book_id",
                Map.of("book_id", book.getId(), "bookname", book.getName(),
                        "genreId", book.getGenre().getId(), "authorId", book.getAuthor().getId()));
    }
}
