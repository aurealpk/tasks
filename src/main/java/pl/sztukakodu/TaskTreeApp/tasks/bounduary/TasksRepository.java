package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;

import java.util.List;

public interface  TasksRepository {

    void add(Task task);

    List<Task> fetchAll();

    Task fetchTaskById(Long id);

    void deleteTask(Long id);

}
