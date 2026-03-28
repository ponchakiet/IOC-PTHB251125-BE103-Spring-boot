package poncha.kiet.btss2.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss2.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users;

    public UserRepository(List<User> users) {
        this.users = new ArrayList<>();
        users.add(new User(1,"Kiet","tuankiet3459@gmail.com","ADMIN"));
        users.add(new User(2,"Nam","tuankiet3459@gmail.com","USER"));
        users.add(new User(3,"Mai","tuankiet3459@gmail.com","USER"));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
