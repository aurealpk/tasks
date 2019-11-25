package pl.sztukakodu.tasks;

import java.util.List;

public interface TaskRepository {

    void add();

    List<Task> fetchAll();

}
