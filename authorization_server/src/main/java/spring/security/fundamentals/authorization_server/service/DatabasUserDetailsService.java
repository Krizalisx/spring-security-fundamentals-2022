package spring.security.fundamentals.authorization_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.security.fundamentals.authorization_server.entity.Authority;
import spring.security.fundamentals.authorization_server.entity.User;
import spring.security.fundamentals.authorization_server.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DatabasUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getAuthorities().stream()
                .map(Authority::getType)
                .map(Enum::toString)
                .map(SimpleGrantedAuthority::new)
                .toList())
            .build();
    }
}
