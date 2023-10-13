package month_1.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;


@Getter
@ConfigurationProperties(prefix = "myapp.file")
public class LocaleProviderImpl implements LocaleProvider {

    private final Locale locale;

    @ConstructorBinding
    public LocaleProviderImpl(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }
}
