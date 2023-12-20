package month_2.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import month_2.domain.Author;
import month_2.domain.Book;
import month_2.domain.Comment;
import month_2.domain.Genre;
import org.bson.Document;

import java.util.List;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "abzalov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "abzalov")
    public void initAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        List<Document> documentList = List.of(
                new Document()
                        .append("firstName", "Lev")
                        .append("lastName", "Tolstoy"),
                new Document()
                        .append("firstName", "Ayn")
                        .append("lastName", "Rand"),
                new Document()
                        .append("firstName", "Robert")
                        .append("lastName", "Lafore"),
                new Document()
                        .append("firstName", "Cay")
                        .append("lastName", "Hortsmann"));
        myCollection.insertMany(documentList);
    }

    @ChangeSet(order = "003", id = "initGenres", author = "abzalov")
    public void initGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genres");
        List<Document> documentList = List.of(
                new Document().append("name", "fantasy"),
                new Document().append("name", "roman")
        );
        myCollection.insertMany(documentList);
    }

    @ChangeSet(order = "004", id = "initBooks", author = "abzalov")
    public void initBooks(MongockTemplate database) {
        List<Genre> genreList = database.findAll(Genre.class);
        List<Author> authorList = database.findAll(Author.class);
        List<Book> bookList = List.of(
                Book.builder().name("War&peace").genre(genreList.get(1)).author(authorList.get(0)).build(),
                Book.builder().name("Atlant").genre(genreList.get(1)).author(authorList.get(1)).build(),
                Book.builder().name("Atlant2").genre(genreList.get(1)).author(authorList.get(1)).build()
        );
        database.insert(bookList, Book.class);
    }

    @ChangeSet(order = "005", id = "initComments", author = "abzalov")
    public void initComments(MongockTemplate database) {
        List<Book> bookList = database.findAll(Book.class);
        List<Comment> commentList = List.of(
                Comment.builder().message("cool").book(bookList.get(0)).build(),
                Comment.builder().message("not cool").book(bookList.get(0)).build(),
                Comment.builder().message("its nice!").book(bookList.get(1)).build(),
                Comment.builder().message("boring!").book(bookList.get(1)).build(),
                Comment.builder().message("amazing!").book(bookList.get(2)).build(),
                Comment.builder().message("Greater!").book(bookList.get(2)).build()
        );
        database.insert(commentList, Comment.class);
    }
}
