package month_2.shell;


import month_2.dto.AuthorDto;
import month_2.dto.BookDto;
import month_2.dto.CommentDto;
import month_2.dto.GenreDto;
import month_2.service.AuthorService;
import month_2.service.BookService;
import month_2.service.CommentService;
import month_2.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellConsole {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final CommentService commentService;

    private String login;

    @Autowired
    public ShellConsole(BookService bookService, AuthorService authorService, GenreService genreService,
                        CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @ShellMethod(key = "login")
    public void login(@ShellOption(defaultValue = "AnyUser") String login) {
        this.login = login;
    }

    public Availability availabilityCheck() {
        return login != null ? Availability.available() : Availability.unavailable("Not authorization");
    }

    @ShellMethod(key = "create-book")
    @ShellMethodAvailability("availabilityCheck")
    public void createBook(@ShellOption String bookName,
                           @ShellOption Long authorId, @ShellOption Long genreId) {
        BookDto bookDto = BookDto.builder()
                .name(bookName)
                .authorDto(AuthorDto.builder().authorId(authorId).build())
                .genreDto(GenreDto.builder().genreId(genreId).build())
                .build();
        System.out.println("Is success! Book is create! " + bookService.create(bookDto));
    }

    @ShellMethod(key = "delete-book")
    @ShellMethodAvailability("availabilityCheck")
    public void deleteBook(@ShellOption long id) {
        bookService.deleteById(id);
        System.out.println("Is success!");
    }

    @ShellMethod(key = "update-book")
    @ShellMethodAvailability("availabilityCheck")
    public void updateBook(@ShellOption Long id, @ShellOption String bookName,
                           @ShellOption Long authorId, @ShellOption Long genreId) {
        BookDto bookDto = BookDto.builder()
                .id(id)
                .name(bookName)
                .authorDto(AuthorDto.builder().authorId(authorId).build())
                .genreDto(GenreDto.builder().genreId(genreId).build())
                .build();
        System.out.println("Is success! Book is update: " + bookService.update(bookDto));
    }

    @ShellMethod(key = "get-book-by-id")
    @ShellMethodAvailability("availabilityCheck")
    public void getBookById(@ShellOption Long id) {
        System.out.println(bookService.getById(id));
    }


    @ShellMethod(key = "get-books")
    @ShellMethodAvailability("availabilityCheck")
    public void getBooks() {
        System.out.println(bookService.getAll());
    }

    @ShellMethod(key = "get-authors")
    @ShellMethodAvailability("availabilityCheck")
    public void getAuthors() {
        System.out.println(authorService.getAll());
    }

    @ShellMethod(key = "get-genres")
    @ShellMethodAvailability("availabilityCheck")
    public void getGenres() {
        System.out.println(genreService.getAll());
    }

    @ShellMethod(key = "get-comments-by-book-id")
    @ShellMethodAvailability("availabilityCheck")
    public void getCommentsByBookId(@ShellOption Long id) {
        System.out.println(commentService.getListByBookId(id));
    }

    @ShellMethod(key = "get-comment-by-id")
    @ShellMethodAvailability("availabilityCheck")
    public void getCommentsById(@ShellOption Long id) {
        System.out.println(commentService.getById(id));
    }

    @ShellMethod(key = "create-comment")
    @ShellMethodAvailability("availabilityCheck")
    public void createComment(@ShellOption String message, @ShellOption Long bookId) {
        CommentDto commentDto = CommentDto
                .builder()
                .message(message)
                .bookId(bookId)
                .build();
        System.out.println(commentService.create(commentDto));
    }

    @ShellMethod(key = "update-comment")
    @ShellMethodAvailability("availabilityCheck")
    public void updateComment(@ShellOption String message, @ShellOption Long bookId,
                              @ShellOption Long commentId) {
        CommentDto commentDto = CommentDto
                .builder()
                .id(commentId)
                .message(message)
                .bookId(bookId)
                .build();
        System.out.println(commentService.update(commentDto));
    }

    @ShellMethod(key = "delete-comment-by-id")
    @ShellMethodAvailability("availabilityCheck")
    public void deleteCommentById(@ShellOption Long id) {
        commentService.deleteById(id);
        System.out.println("Is success!");
    }
}
