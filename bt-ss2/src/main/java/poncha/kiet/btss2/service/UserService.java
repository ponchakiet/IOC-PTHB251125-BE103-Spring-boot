package poncha.kiet.btss2.service;

import org.springframework.stereotype.Service;
import poncha.kiet.btss2.model.User;
import poncha.kiet.btss2.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id);
    }
}
