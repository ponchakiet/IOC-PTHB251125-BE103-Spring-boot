package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.request.RoleUpdateRequest;
import re.edu.dto.response.UserResponse;
import re.edu.entity.User;
import re.edu.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Set<String> ALLOWED_ROLES = Set.of("ROLE_CUSTOMER", "ROLE_STAFF", "ROLE_ADMIN");

    private final UserRepository userRepository;

    public UserResponse getMe(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
        return toResponse(user);
    }

    public UserResponse updateRole(Long targetId, RoleUpdateRequest request) {
        String newRole = request.getRole();
        if (!ALLOWED_ROLES.contains(newRole)) {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }

        User user = userRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + targetId));

        user.setRole(newRole);
        return toResponse(userRepository.save(user));
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
