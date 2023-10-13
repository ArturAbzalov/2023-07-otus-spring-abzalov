package month_1.config;

import month_1.service.ConsoleIOService;
import month_1.service.IOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfig {

    @Bean
    public IOService ioService() {
        return new ConsoleIOService(System.out, System.in);
    }

}
