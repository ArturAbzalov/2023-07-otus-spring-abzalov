package month_1.service;

import month_1.config.LocaleProvider;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocalizationServiceImpl implements LocalizationService {

    private final LocaleProvider localeProviderImpl;

    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleProvider localeProviderImpl, MessageSource messageSource) {
        this.localeProviderImpl = localeProviderImpl;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key, Object...args) {
        return messageSource.getMessage(key, args, localeProviderImpl.getLocale());
    }
}
