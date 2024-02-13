package spring.security.fundamentals.security.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import spring.security.fundamentals.security.provider.ApiKeyAuthenticationProvider;

@RequiredArgsConstructor
public class ApiKeyAuthenticationManager implements AuthenticationManager {

    private final ApiKeyAuthenticationProvider provider; // there can be multiple providers

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
        }

        throw new BadCredentialsException("The provided key is not correct");
    }
}
