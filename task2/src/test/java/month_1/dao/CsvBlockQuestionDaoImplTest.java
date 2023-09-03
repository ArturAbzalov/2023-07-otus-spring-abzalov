package month_1.dao;

import month_1.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Проверка класса CsvBlockQuestionsDaoImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class CsvBlockQuestionDaoImplTest {

    @Autowired
    private BlockQuestionDao blockQuestionDao;

    @Test
    @DisplayName("Проверка размерности массива")
    public void getBlockQuestionsSizeTest() {
        Assertions.assertEquals(5, blockQuestionDao.getBlockQuestions().size());
    }
}
