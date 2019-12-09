package pl.sztukakodu.TaskTreeApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TasketteConfiguration {

    @Bean
    public Clock clock() {
        log.info("Registering clock as Spring Bean");
        return new SystemClock();
    }

}
