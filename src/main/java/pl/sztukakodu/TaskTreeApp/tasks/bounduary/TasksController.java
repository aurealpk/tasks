package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sztukakodu.TaskTreeApp.tasks.TasksConfig;
import pl.sztukakodu.TaskTreeApp.tasks.control.TasksService;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;
    private final TasksService tasksService;
    private final TasksConfig config;

    public TasksController(TasksRepository tasksRepository, TasksService tasksService, TasksConfig config) {
        this.tasksRepository = tasksRepository;
        this.tasksService = tasksService;
        this.config = config;
    }

    @PostConstruct
    private void init() {
        tasksService.addTask("Zadanie 1", "Wykonać zdanie 1");
        tasksService.addTask("Zadanie 2", "Wykonać zdanie 2");
        tasksService.addTask("Zadanie 3", "Wykonać zdanie 3");
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        log.info("Fetching all tasks...");
        return tasksRepository.fetchAll()
                .stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/hello")
    public String getMessage() {
        log.info("Message");
        return config.getEndpointMessage();
    }

    @GetMapping(path = "/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        log.info("Fetch Task by id {}", id);
        return toTaskResponse(tasksRepository.fetchTaskById(id));
    }

    @PostMapping
    public void addTask(@RequestBody CreateTaskRequest task) {
        log.info("Storing new task: {}", task );
        tasksService.addTask(task.title, task.description);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable Long id){
        log.info("Deleting task by id {}", id);
        tasksRepository.deleteTask(id);
    }

    @PutMapping
    public void updateTask(){
        log.info("Updating task");
    }

    private TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                LocalDateTime.now()
        );
    }



}
