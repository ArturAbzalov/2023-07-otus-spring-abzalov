package month_1.service.integration;

import month_1.config.TestConfig;
import month_1.dao.BlockQuestionDao;
import month_1.dao.StudentDao;
import month_1.dao.StudentDaoImpl;
import month_1.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Интеграционный тест класса StartAppImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, initializers = ConfigDataApplicationContextInitializer.class)
public class StartAppImplTest {

    @Autowired
    private BlockQuestionDao blockQuestionDao;

    @Mock
    private LocalizationService localizationService;

    private IOService ioService;

    private StudentDao studentDao;

    private ByteArrayOutputStream byteArrayOutputStream;

    private ByteArrayInputStream byteArrayInputStream;

    private StartApp startApp;


    @BeforeEach
    public void setUp() {
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
        ioService = new ConsoleIOService(byteArrayOutputStream, System.in);
        studentDao = new StudentDaoImpl(ioService, localizationService);
        startApp = new StartAppImpl(blockQuestionDao, ioService, studentDao, localizationService);
    }

    @Test
    public void getStartTestEn() {
        when(localizationService.getMessage("inputFirstName")).thenReturn("Input your first name:");
        when(localizationService.getMessage("inputLastName")).thenReturn("Input your last name:");
        when(localizationService.getMessage("warningInputNumber")).thenReturn("Input number range 1 of {0}:");
        when(localizationService.getMessage("registeredAnswer")).thenReturn("Your answer registered!");
        when(localizationService.getMessage("inputNumberAnswer")).thenReturn("Enter your number answer:");
        when(localizationService.getMessage("success")).thenReturn("Great job, {0}! Your scores: {1}");
        when(localizationService.getMessage("wasted")).thenReturn("Sorry, {0}...try again. Your scores: {1}");
        when(localizationService.getMessage("exceptionParse")).thenReturn("Exception parse file");
        startApp.getStart();
        String g = """
                Input your first name:\s
                Input your last name:\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Your answer registered!\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Your answer registered!\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Your answer registered!\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Your answer registered!\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Your answer registered!\s
                Great job, George Alan! Your scores: 5""";
        Assertions.assertEquals(g, byteArrayOutputStream.toString());
    }

    @Test
    public void getStartTestRu() {
        when(localizationService.getMessage("inputFirstName")).thenReturn("Введите свое имя:");
        when(localizationService.getMessage("inputLastName")).thenReturn("Введите свою фамилию:");
        when(localizationService.getMessage("warningInputNumber")).thenReturn("Введите число от 1 до {0}:");
        when(localizationService.getMessage("registeredAnswer")).thenReturn("Ваш ответ принят");
        when(localizationService.getMessage("inputNumberAnswer")).thenReturn("Введите номер ответа:");
        when(localizationService.getMessage("success")).thenReturn("Хорошая работа, {0}! Вы набрали: {1}");
        when(localizationService.getMessage("wasted")).thenReturn("Извините, {0}...попробуйте ещё раз. Вы набрали: {1}");
        when(localizationService.getMessage("exceptionParse")).thenReturn("Ошибка при обработке файла");
        startApp.getStart();
        String g = """
                Введите свое имя:\s
                Введите свою фамилию:\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Введите номер ответа:Ваш ответ принят\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Введите номер ответа:Ваш ответ принят\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Введите номер ответа:Ваш ответ принят\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Введите номер ответа:Ваш ответ принят\s
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Введите номер ответа:Ваш ответ принят\s
                Хорошая работа, George Alan! Вы набрали: 5""";
        Assertions.assertEquals(g, byteArrayOutputStream.toString());
    }
}
