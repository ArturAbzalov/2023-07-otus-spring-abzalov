package month_1.service.integration;

import month_1.config.TestConfig;
import month_1.dao.BlockQuestionDao;
import month_1.dao.StudentDao;
import month_1.dao.StudentDaoImpl;
import month_1.service.ConsoleIOService;
import month_1.service.IOService;
import month_1.service.StartApp;
import month_1.service.StartAppImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@DisplayName("Интеграционный тест класса StartAppImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class StartAppImplTest {

    @Autowired
    private BlockQuestionDao blockQuestionDao;

    private IOService ioService;

    private StudentDao studentDao;

    private ByteArrayOutputStream byteArrayOutputStream;

    private ByteArrayInputStream byteArrayInputStream;

    private StartApp startApp;

    @BeforeEach
    public void set() {
        byteArrayInputStream = new ByteArrayInputStream(("""
                Alan
                George
                4
                4
                4
                4
                4
                """).getBytes());
        System.setIn(byteArrayInputStream);
        byteArrayOutputStream = new ByteArrayOutputStream();
        ioService = new ConsoleIOService(System.in, byteArrayOutputStream);
        studentDao = new StudentDaoImpl(ioService);
        startApp = new StartAppImpl(blockQuestionDao, ioService, studentDao);
    }

    @Test
    public void getStartTest() {
        startApp.getStart();
        String g = """
                Введите свое имя:
                Введите свою фамилию:
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Ваш ответ принят
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Ваш ответ принят
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Ваш ответ принят
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Ваш ответ принят
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Ваш ответ принят
                Great job, George Alan! Your scores: 5""";
        Assertions.assertEquals(g, byteArrayOutputStream.toString());
    }
}
