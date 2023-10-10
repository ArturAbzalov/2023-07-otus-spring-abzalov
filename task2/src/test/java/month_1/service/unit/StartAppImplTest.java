package month_1.service.unit;

import month_1.dao.BlockQuestionDao;
import month_1.dao.CsvBlockQuestionDaoImpl;
import month_1.dao.StudentDao;
import month_1.dao.StudentDaoImpl;
import month_1.model.Answer;
import month_1.model.BlockQuestion;
import month_1.model.Student;
import month_1.service.ConsoleIOService;
import month_1.service.IOService;
import month_1.service.StartApp;
import month_1.service.StartAppImpl;
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

    private final List<BlockQuestion> blockQuestionListMock =
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

    private IOService ioService;

    private BlockQuestionDao blockQuestionDao;

    private StudentDao studentDao;

    private ByteArrayOutputStream byteArrayOutputStream;

    private ByteArrayInputStream byteArrayInputStream;


    @BeforeEach
    public void set() {
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
        ioService = new ConsoleIOService(System.in, byteArrayOutputStream);
        blockQuestionDao = mock(CsvBlockQuestionDaoImpl.class);
        studentDao = mock(StudentDaoImpl.class);
    }

    @Test
    public void getStartTest() {
        when(blockQuestionDao.getBlockQuestions()).thenReturn(blockQuestionListMock);
        when(studentDao.create()).thenReturn(student);
        StartApp startApp = new StartAppImpl(blockQuestionDao, ioService, studentDao);
        startApp.getStart();
        String expected = """
                Why is a raven like a writing desk?
                1.Nothing
                2.Nothing
                3.Nothing
                4.Nothing
                Enter your number answer: Введите число от 1 до 4:\s
                Введите число от 1 до 4:\s
                Ваш ответ принят
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
                Great job, Alan George! Your scores: 4""";
        Assertions.assertEquals(expected, byteArrayOutputStream.toString());
    }
}
