package month_1.service;

import month_1.config.LocaleConfig;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocalizationServiceImpl implements LocalizationService {

    private final LocaleConfig localeConfigImpl;

    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleConfig localeConfigImpl, MessageSource messageSource) {
        this.localeConfigImpl = localeConfigImpl;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key, Object...args) {
        return messageSource.getMessage(key, args, localeConfigImpl.getLocale());
    }
}
