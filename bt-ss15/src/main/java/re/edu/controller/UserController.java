package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.RoleUpdateRequest;
import re.edu.dto.response.UserResponse;
import re.edu.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getMe(userDetails.getUsername()));
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateRole(@PathVariable Long id,
                                                   @RequestBody RoleUpdateRequest request) {
        return ResponseEntity.ok(userService.updateRole(id, request));
    }
}
