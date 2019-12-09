package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sztukakodu.TaskTreeApp.tasks.TasksConfig;
import pl.sztukakodu.TaskTreeApp.tasks.control.TasksService;
import pl.sztukakodu.TaskTreeApp.tasks.entity.Task;
import pl.sztukakodu.TaskTreeApp.tasks.exceptions.NotFoundExcpetion;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
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
    public List<TaskResponse> getTasks(@RequestParam Optional<String> query) {
        log.info("Fetching all tasks... by query: {}", query);
        return query.map(q -> tasksService.filterAllByQuery(q))
                .orElseGet(() -> tasksService.fetchAll())
                .stream()
                .map(task -> toTaskResponse(task))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/hello")
    public String getMessage() {
        log.info("Message");
        return config.getEndpointMessage();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        try {
            log.info("Fetch Task by id {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(toTaskResponse(tasksService.fetchTaskById(id)));
        } catch (IllegalArgumentException e) {
            log.info("Not found task {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody CreateTaskRequest task) {
            log.info("Storing new task: {}", task );
            tasksService.addTask(task.title, task.description);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            log.info("Deleting task by id {}", id);
            tasksService.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundExcpetion e){
            log.info("Failed to delete task {} exception {}",id, e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        try {
            log.info("Updating task o id", id);
            tasksService.updateTask(id, request.title, request.description);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExcpetion e) {
            log.error("Failed to update task {}", id);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    private TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt()
        );
    }
}
