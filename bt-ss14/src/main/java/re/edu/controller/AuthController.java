package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.JwtResponse;
import re.edu.dto.LoginRequest;
import re.edu.dto.RegisterRequest;
import re.edu.model.User;
import re.edu.security.JwtProvider;
import re.edu.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @GetMapping("/test")
    public String test() {
        return "Public API - OK";
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest dto) {
        User saved = authService.register(dto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            String token = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(token, dto.getUsername()));

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("username or password incorrect");
        }
    }
}
