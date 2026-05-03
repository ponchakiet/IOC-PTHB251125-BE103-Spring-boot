package re.edu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import re.edu.config.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/orders").hasRole("CUSTOMER")
                        .requestMatchers("/api/orders/my").hasRole("CUSTOMER")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/orders").hasAnyRole("STAFF", "ADMIN")
                        .requestMatchers("/api/orders/*/status").hasRole("STAFF")
                        .requestMatchers("/api/users/me").authenticated()
                        .requestMatchers("/api/users/*/role").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/reviews").hasRole("CUSTOMER")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/products/*/reviews").permitAll()
                        .requestMatchers("/api/reports/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
