package pl.sztukakodu.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class TasksController {

    private final TasksConfig config;
    private final TasksRepository tasksRepository;

    public TasksController(TasksConfig config, TasksRepository tasksRepository) {
        this.config = config;
        this.tasksRepository = tasksRepository;
    }

    @GetMapping
    public List<Task> getTasks(){
        return tasksRepository.fetchAll();
    }

    @PostMapping
    public void addTask(Task task) {
        tasksRepository.add(task);
    }
}
