package month_1.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Optional;

@ConfigurationProperties(prefix = "myapp.resource-set")
public class ResourceProviderTest implements ResourceProvider {
    private Map<String, String> csvResourceMap;

    private final LocaleProviderTest localeProvider;

    @Autowired
    public ResourceProviderTest(LocaleProviderTest localeProvider) {
        this.localeProvider = localeProvider;
    }

    public void setCsvResourceMap(Map<String, String> csvResourceMap) {
        this.csvResourceMap = csvResourceMap;
    }


    @Override
    public String getPath() {
        return Optional.ofNullable(csvResourceMap.get(localeProvider.getLocale().toString()))
                .orElse("blockQuestions_En.csv");
    }
}
