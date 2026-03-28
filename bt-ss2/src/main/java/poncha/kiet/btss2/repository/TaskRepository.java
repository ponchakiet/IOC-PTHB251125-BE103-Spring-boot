package poncha.kiet.btss2.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss2.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i, "Task " + i, "Mô tả công việc " + i, i % 2 == 0 ? "high" : "low", 1));
        }
    }

    public List<Task> findAll() {
        return tasks;
    }

    public void save(Task task) {
        tasks.add(task);
    }
}
