import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StartApp;

public class Main {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext.xml")
                .getBean(StartApp.class).getStart();
    }
}
