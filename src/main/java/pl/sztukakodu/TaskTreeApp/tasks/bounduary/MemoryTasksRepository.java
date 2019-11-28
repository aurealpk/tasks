package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import org.springframework.stereotype.Component;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MemoryTasksRepository implements TasksRepository {

    private Set<Task> tasks  = new HashSet<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public  Task fetchTaskById(Long id) {
       return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void deleteTask(Long id) {
        tasks.stream().filter(task -> id.equals(task.getId())).findFirst().ifPresent(task -> tasks.remove(task));
    }

}
