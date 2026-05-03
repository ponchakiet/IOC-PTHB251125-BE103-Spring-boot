package re.edu.config.principal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import re.edu.entity.User;
import re.edu.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

        return UserDetailCustom.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
