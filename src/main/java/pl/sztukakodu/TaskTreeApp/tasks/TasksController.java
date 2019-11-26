package pl.sztukakodu.TaskTreeApp.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/")
public class TasksController {

    private final TasksRepository tasksRepository;
    private final TasksConfig config;

    public TasksController(TasksRepository tasksRepository, TasksConfig config) {
        this.tasksRepository = tasksRepository;
        this.config = config;
    }

    @GetMapping
    public List<Task> getTasks() {
        log.info("Fetching all tasks...");
        return tasksRepository.fetchAll();
    }
    @GetMapping(path = "/hello")
    public String getMessage() {
        log.info("Message");
        return config.getEndpointMessage();
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        log.info("Storing new task: {}", task );
        tasksRepository.add(task);
    }


}
