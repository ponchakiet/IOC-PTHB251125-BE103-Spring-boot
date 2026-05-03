package re.edu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import re.edu.config.security.UserDetailServiceCustom;
import re.edu.security.JwtAuthenticationEntryPoint;
import re.edu.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailServiceCustom customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)   // tắt cors
                .csrf(AbstractHttpConfigurer::disable) // tắt csrf
                // phân quyền cho các API theo đường dẫn
                .authorizeHttpRequests(
                        req->
                                req.requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // admin ms đc truy cập api này
                                        .requestMatchers("/api/v1/user/**").hasRole("USER")
                                        .requestMatchers("/api/v1/manager/**").hasRole("MANAGER")
                                        .requestMatchers("/api/v1/auth/**").permitAll()
                                        .anyRequest().authenticated() // các api khác thì phải xác thực thì ms vào đc
                )
                // cơ chế dăng nhâp http basic
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
