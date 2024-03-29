package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import org.springframework.stereotype.Component;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;
import pl.sztukakodu.TaskTreeApp.tasks.exceptions.NotFoundExcpetion;

import java.util.*;

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
       return findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void deleteTask(Long id) {
        findById(id).ifPresent(task -> tasks.remove(task));
    }


    @Override
    public void update(Long id, String title, String description) {
        Task task = findById(id)
                .orElseThrow(() -> new NotFoundExcpetion("Taski with id not found: " + id));
        task.setTitle(title);
        task.setDescription(description);
    }

    private Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> id.equals(task.getId())).findFirst();
    }

}
