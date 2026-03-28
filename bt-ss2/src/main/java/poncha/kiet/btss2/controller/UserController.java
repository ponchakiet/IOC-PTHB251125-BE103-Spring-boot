package poncha.kiet.btss2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poncha.kiet.btss2.model.Task;
import poncha.kiet.btss2.model.User;
import poncha.kiet.btss2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam String search) {
        List<User> users = userService.findAllUsers();
        if(search!=null&&!search.isEmpty()){
            List<User> filteredUsers = users.stream().filter(user -> user.getUsername().toLowerCase().contains((search.toLowerCase()))).toList();
            return new ResponseEntity<>(filteredUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
