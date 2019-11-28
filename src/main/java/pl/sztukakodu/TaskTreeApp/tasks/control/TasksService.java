package pl.sztukakodu.TaskTreeApp.tasks.control;

import org.springframework.stereotype.Service;
import pl.sztukakodu.TaskTreeApp.tasks.bounduary.TasksRepository;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final AtomicLong nextTaskId = new AtomicLong(0L);

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void addTask(String title, String description) {
        tasksRepository.add(
                new Task(
                        nextTaskId.incrementAndGet(),
                        title,
                        description
                        )
        );
    }
}
