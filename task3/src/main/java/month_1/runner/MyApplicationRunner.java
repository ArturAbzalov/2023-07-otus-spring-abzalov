package month_1.runner;

import month_1.service.StartApp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final StartApp startApp;

    public MyApplicationRunner(StartApp startApp) {
        this.startApp = startApp;
    }

    @Override
    public void run(ApplicationArguments args) {
        startApp.getStart();
    }
}
