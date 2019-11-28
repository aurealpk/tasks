package pl.sztukakodu.TaskTreeApp.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    long id;
    String title;
    String description;
}
