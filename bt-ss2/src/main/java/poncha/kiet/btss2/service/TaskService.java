package poncha.kiet.btss2.service;

import org.springframework.stereotype.Service;
import poncha.kiet.btss2.model.Task;
import poncha.kiet.btss2.model.User;
import poncha.kiet.btss2.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task newTask) {
        User assignedUser = userService.findUserById(newTask.getAssignedTo());

        if (assignedUser != null) {
            taskRepository.save(newTask);
            return newTask;
        }
        return null;
    }
}
