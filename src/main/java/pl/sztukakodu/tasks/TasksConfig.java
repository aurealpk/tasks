package pl.sztukakodu.tasks;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.tasks")
public class TaskConfig {

    private String endpointMessage;

    public String getEndpointMessage() {
        return endpointMessage;
    }
}
