package pl.sztukakodu.TaskTreeApp.tasks.control;

import org.springframework.stereotype.Service;
import pl.sztukakodu.TaskTreeApp.Clock;
import pl.sztukakodu.TaskTreeApp.tasks.bounduary.TasksRepository;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final Clock clock;
    private final AtomicLong nextTaskId = new AtomicLong(0L);

    public TasksService(TasksRepository tasksRepository, Clock clock) {
        this.tasksRepository = tasksRepository;
        this.clock = clock;
    }

    public void addTask(String title, String description) {
        tasksRepository.add(
                new Task(
                        nextTaskId.incrementAndGet(),
                        title,
                        description,
                        clock.time()
                        )
        );
    }

    public void updateTask(Long id, String title, String description) {
        tasksRepository.update(id, title,description);
    }

    public List<Task> fetchAll() {
        return tasksRepository.fetchAll();
    }

    public Task fetchTaskById(Long id) {
        return tasksRepository.fetchTaskById(id);
    }

    public List<Task> filterAllByQuery(String query) {
        return tasksRepository.fetchAll()
                .stream()
                .filter(task -> {
                    return task.getTitle().contains(query) ||
                           task.getDescription().contains(query);
                })
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteTask(id);
    }
}
