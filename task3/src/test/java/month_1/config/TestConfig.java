package month_1.config;

import month_1.dao.BlockQuestionDao;
import month_1.dao.CsvBlockQuestionDaoImpl;
import month_1.service.LocalizationService;
import month_1.service.LocalizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LocaleConfigTest.class)
public class TestConfig {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleConfigTest localeConfig;


    @Bean
    public LocalizationService localizationService() {
        return new LocalizationServiceImpl(localeConfig, messageSource);
    }

    @Bean
    public BlockQuestionDao blockQuestionDao() {
        return new CsvBlockQuestionDaoImpl(localeConfig, localizationService());
    }

}
