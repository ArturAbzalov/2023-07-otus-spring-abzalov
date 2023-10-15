package month_2.shell;


import month_2.service.AuthorService;
import month_2.service.BookService;
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

    private String login;

    @Autowired
    public ShellConsole(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
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
                           @ShellOption String firstName, @ShellOption String lastName, @ShellOption String genreName) {
        bookService.createBook(bookName, firstName, lastName, genreName);
        System.out.println("Is success!");
    }

    @ShellMethod(key = "delete-book")
    @ShellMethodAvailability("availabilityCheck")
    public void deleteBook(@ShellOption long id) {
        bookService.deleteBookById(id);
        System.out.println("Is success!");
    }

    @ShellMethod(key = "update-book")
    @ShellMethodAvailability("availabilityCheck")
    public void updateBook(@ShellOption String bookName,
                           @ShellOption String firstName, @ShellOption String lastName, @ShellOption String genreName) {
        bookService.updateBook(bookName, firstName, lastName, genreName);
        System.out.println("Is success!");
    }

    @ShellMethod(key = "get-books")
    @ShellMethodAvailability("availabilityCheck")
    public void getBooks() {
        System.out.println(bookService.getAllBooks());
    }

    @ShellMethod(key = "get-authors")
    @ShellMethodAvailability("availabilityCheck")
    public void getAuthors() {
        System.out.println(authorService.getAllAuthors());
    }

    @ShellMethod(key = "get-genres")
    @ShellMethodAvailability("availabilityCheck")
    public void getGenres() {
        System.out.println(genreService.getAllGenres());
    }
}
