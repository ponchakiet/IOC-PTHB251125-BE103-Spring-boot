package poncha.kiet.btss2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss2.model.Task;
import poncha.kiet.btss2.model.User;
import poncha.kiet.btss2.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam String search) {
        List<Task> tasks = taskService.findAllTasks();
        if(search!=null&&!search.isEmpty()){
            List<Task> filteredTasks = tasks.stream().filter(task -> task.getTitle().toLowerCase().contains((search.toLowerCase()))).toList();
            return new ResponseEntity<>(filteredTasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        Task createTask = taskService.saveTask(newTask);

        if(createTask!=null){
            return new ResponseEntity<>(createTask, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
