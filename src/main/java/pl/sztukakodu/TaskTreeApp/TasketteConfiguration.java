package pl.sztukakodu.TaskTreeApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sztukakodu.TaskTreeApp.tasks.bounduary.FileSystemStorageService;
import pl.sztukakodu.TaskTreeApp.tasks.bounduary.StorageService;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class TasketteConfiguration {

    @Bean
    public Clock clock() {
        log.info("Registering clock as Spring Bean");
        return new SystemClock();
    }

    @Bean
    StorageService storageService() {
        return new FileSystemStorageService(
                Path.of("/home/ki/Dokumenty/tasks"));
    }

}
