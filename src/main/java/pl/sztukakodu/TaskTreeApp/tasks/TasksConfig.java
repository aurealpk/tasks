package pl.sztukakodu.TaskTreeApp.tasks;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.tasks")
public class TasksConfig {

    private String endpointMessage;

    public String getEndpointMessage() {
        return endpointMessage;
    }
}
