package pl.sztukakodu.TaskTreeApp.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
