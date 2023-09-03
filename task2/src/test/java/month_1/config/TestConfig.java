package month_1.config;

import month_1.dao.BlockQuestionDao;
import month_1.dao.CsvBlockQuestionDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:application.properties")
public class TestConfig {

    @Value("${file.test.csv}")
    private Resource csvFile;

    @Bean
    public BlockQuestionDao blockQuestionDao() {
        return new CsvBlockQuestionDaoImpl(csvFile);
    }

}
