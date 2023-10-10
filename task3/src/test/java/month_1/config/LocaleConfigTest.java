package month_1.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;


@Getter
@ConfigurationProperties(prefix = "fileset")
public class LocaleConfigTest implements LocaleConfig {

    private final Map<String, String> csvResourceMap;

    private final Locale locale;

    @ConstructorBinding
    public LocaleConfigTest(Map<String, String> csvResourceMap, String locale) {
        this.csvResourceMap = csvResourceMap;
        this.locale = Locale.forLanguageTag(locale);
    }

    public String getPath() {
        return Optional.ofNullable(csvResourceMap.get(getLocale().toString()))
                .orElse("blockQuestions_En.csv");
    }

}
