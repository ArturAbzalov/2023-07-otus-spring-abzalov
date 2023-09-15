package month_1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import month_1.service.StartApp;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Main.class)
                .getBean(StartApp.class).getStart();
    }
}
