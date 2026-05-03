package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import re.edu.dto.LoginRequest;
import re.edu.dto.RegisterRequest;
import re.edu.model.User;
import re.edu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username '" + dto.getUsername() + "' đã tồn tại!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // $2a$10$...
        user.setFullName(dto.getFullName());
        user.setRole("USER");
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public User login(LoginRequest dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("username or password incorrect"));
        boolean matched = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!matched) {
            throw new RuntimeException("username or password incorrect");
        }
        return user;
    }
}
