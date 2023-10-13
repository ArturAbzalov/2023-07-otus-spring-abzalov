package month_1.service.unit;

import month_1.dao.BlockQuestionDao;
import month_1.dao.CsvBlockQuestionDaoImpl;
import month_1.dao.StudentDao;
import month_1.dao.StudentDaoImpl;
import month_1.model.Answer;
import month_1.model.BlockQuestion;
import month_1.model.Student;
import month_1.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Юнит тест класса StartAppImpl")
public class StartAppImplTest {

    private final Student student = new Student()
            .setFirstName("Alan")
            .setLastName("George");

    private final List<BlockQuestion> blockQuestionListMockEn =
            new ArrayList<>() {{
                this.add(BlockQuestion.builder()
                        .question("Why is a raven like a writing desk?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Nothing", true));
                            this.put(2, new Answer("Nothing", true));
                            this.put(3, new Answer("Nothing", true));
                            this.put(4, new Answer("Nothing", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Why is a raven like a writing desk?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Nothing", true));
                            this.put(2, new Answer("Nothing", true));
                            this.put(3, new Answer("Nothing", true));
                            this.put(4, new Answer("Nothing", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Why is a raven like a writing desk?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Nothing", true));
                            this.put(2, new Answer("Nothing", true));
                            this.put(3, new Answer("Nothing", true));
                            this.put(4, new Answer("Nothing", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Why is a raven like a writing desk?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Nothing", true));
                            this.put(2, new Answer("Nothing", true));
                            this.put(3, new Answer("Nothing", true));
                            this.put(4, new Answer("Nothing", true));
                        }})
                        .build());
            }};
    private final List<BlockQuestion> blockQuestionListMockRu =
            new ArrayList<>() {{
                this.add(BlockQuestion.builder()
                        .question("Что общего у ворона и письменного стола?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Ничего", true));
                            this.put(2, new Answer("Ничего", true));
                            this.put(3, new Answer("Ничего", true));
                            this.put(4, new Answer("Ничего", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Что общего у ворона и письменного стола?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Ничего", true));
                            this.put(2, new Answer("Ничего", true));
                            this.put(3, new Answer("Ничего", true));
                            this.put(4, new Answer("Ничего", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Что общего у ворона и письменного стола?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Ничего", true));
                            this.put(2, new Answer("Ничего", true));
                            this.put(3, new Answer("Ничего", true));
                            this.put(4, new Answer("Ничего", true));
                        }})
                        .build());
                this.add(BlockQuestion.builder()
                        .question("Что общего у ворона и письменного стола?")
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer("Ничего", true));
                            this.put(2, new Answer("Ничего", true));
                            this.put(3, new Answer("Ничего", true));
                            this.put(4, new Answer("Ничего", true));
                        }})
                        .build());
            }};

    private IOService ioService;

    private BlockQuestionDao blockQuestionDao;

    private StudentDao studentDao;

    private ByteArrayOutputStream byteArrayOutputStream;

    private ByteArrayInputStream byteArrayInputStream;

    private LocalizationService localizationService;

    private StartApp startApp;


    @BeforeEach
    public void setUp() {
        byteArrayInputStream = new ByteArrayInputStream(("""
                My string
                My
                4
                4
                4
                4
                4
                """).getBytes());
        System.setIn(byteArrayInputStream);
        byteArrayOutputStream = new ByteArrayOutputStream();
        ioService = new ConsoleIOService(byteArrayOutputStream,System.in);
        blockQuestionDao = mock(CsvBlockQuestionDaoImpl.class);
        studentDao = mock(StudentDaoImpl.class);
        localizationService = mock(LocalizationServiceImpl.class);
        startApp = new StartAppImpl(blockQuestionDao, ioService, studentDao, localizationService);
    }

    @Test
    public void getStartTestEn() {
        when(blockQuestionDao.getBlockQuestions()).thenReturn(blockQuestionListMockEn);
        when(studentDao.create()).thenReturn(student);
        when(localizationService.getMessage("inputFirstName")).thenReturn("Input your first name:");
        when(localizationService.getMessage("inputLastName")).thenReturn("Input your last name:");
        when(localizationService.getMessage("warningInputNumber")).thenReturn("Input number range 1 of {0}:");
        when(localizationService.getMessage("registeredAnswer")).thenReturn("Your answer registered!");
        when(localizationService.getMessage("inputNumberAnswer")).thenReturn("Enter your number answer:");
        when(localizationService.getMessage("success")).thenReturn("Great job, {0}! Your scores: {1}");
        when(localizationService.getMessage("wasted")).thenReturn("Sorry, {0}...try again. Your scores: {1}");
        when(localizationService.getMessage("exceptionParse")).thenReturn("Exception parse file");
        startApp.getStart();
        String expectedEn = """
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer:Input number range 1 of 4:\s
                Input number range 1 of 4:\s
                Your answer registered!\s
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
                Great job, Alan George! Your scores: 4""";
        Assertions.assertEquals(expectedEn, byteArrayOutputStream.toString());
    }

    @Test
    public void getStartTestRu() {
        when(blockQuestionDao.getBlockQuestions()).thenReturn(blockQuestionListMockRu);
        when(studentDao.create()).thenReturn(student);
        when(localizationService.getMessage("inputFirstName")).thenReturn("Введите свое имя:");
        when(localizationService.getMessage("inputLastName")).thenReturn("Введите свою фамилию:");
        when(localizationService.getMessage("warningInputNumber")).thenReturn("Введите число от 1 до {0}:");
        when(localizationService.getMessage("registeredAnswer")).thenReturn("Ваш ответ принят");
        when(localizationService.getMessage("inputNumberAnswer")).thenReturn("Введите номер ответа:");
        when(localizationService.getMessage("success")).thenReturn("Хорошая работа, {0}! Вы набрали: {1}");
        when(localizationService.getMessage("wasted")).thenReturn("Извините, {0}...попробуйте ещё раз. Вы набрали: {1}");
        when(localizationService.getMessage("exceptionParse")).thenReturn("Ошибка при обработке файла");
        startApp.getStart();
        String expectedRu = """
                Что общего у ворона и письменного стола?
                1.Ничего
                2.Ничего
                3.Ничего
                4.Ничего
                Введите номер ответа:Введите число от 1 до 4:\s
                Введите число от 1 до 4:\s
                Ваш ответ принят\s
                Что общего у ворона и письменного стола?
                1.Ничего
                2.Ничего
                3.Ничего
                4.Ничего
                Введите номер ответа:Ваш ответ принят\s
                Что общего у ворона и письменного стола?
                1.Ничего
                2.Ничего
                3.Ничего
                4.Ничего
                Введите номер ответа:Ваш ответ принят\s
                Что общего у ворона и письменного стола?
                1.Ничего
                2.Ничего
                3.Ничего
                4.Ничего
                Введите номер ответа:Ваш ответ принят\s
                Хорошая работа, Alan George! Вы набрали: 4""";
        Assertions.assertEquals(expectedRu, byteArrayOutputStream.toString());
    }
}
