package pl.sztukakodu.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class TaskController {

    private final TaskConfig config;

    @GetMapping
    public String getTasks(){
        //zwróci listę zadań w systemie
    }



}
