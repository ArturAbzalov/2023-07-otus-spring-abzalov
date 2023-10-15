package month_1.shell;


import month_1.service.StartApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommand {

    private final StartApp startApp;

    private String login;

    @Autowired
    public ShellCommand(StartApp startApp) {
        this.startApp = startApp;
    }

    @ShellMethod(key = "start-test")
    @ShellMethodAvailability("availabilityCheck")
    public void getStart() {
        startApp.getStart();
    }

    @ShellMethod(key = "login")
    public void login(@ShellOption(defaultValue = "AnyUser") String login) {
        this.login = login;
    }

    public Availability availabilityCheck() {
        return login != null ? Availability.available() : Availability.unavailable("Not authorization");
    }
}
