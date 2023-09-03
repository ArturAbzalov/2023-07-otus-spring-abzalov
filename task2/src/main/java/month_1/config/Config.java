package month_1.config;

import month_1.dao.BlockQuestionDao;
import month_1.dao.CsvBlockQuestionDaoImpl;
import month_1.service.ConsoleIOService;
import month_1.service.IOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${file.csv}")
    private Resource resource;

    @Bean
    public IOService ioService() {
        return new ConsoleIOService(System.in, System.out);
    }

    @Bean
    public BlockQuestionDao blockQuestionDao() {
        return new CsvBlockQuestionDaoImpl(resource);
    }
}
