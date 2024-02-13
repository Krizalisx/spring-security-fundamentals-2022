package spring.security.fundamentals.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.security.fundamentals.security.authentication.ApiKeyAuthentication;
import spring.security.fundamentals.security.manager.ApiKeyAuthenticationManager;
import spring.security.fundamentals.security.provider.ApiKeyAuthenticationProvider;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final ApiKeyAuthenticationManager apiKeyAuthenticationManager;

    public ApiKeyAuthenticationFilter(@Value("${our.very.very.very.secret.key}") String secretKey) {
        this.apiKeyAuthenticationManager = new ApiKeyAuthenticationManager(new ApiKeyAuthenticationProvider(secretKey));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // create an authentication object which is not yet authenticated
        // delegate the authentication object to the manager
        // get back the authentication from the manager
        // if the object is authenticated, then send request to the next filter in the chain
        String key = String.valueOf(request.getHeader("x-api-key"));
        if ("null".equals(key) || key == null) {
            filterChain.doFilter(request, response);
            return;
        }

        ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(false, key);

        try {
            Authentication authentication = apiKeyAuthenticationManager.authenticate(apiKeyAuthentication);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
        } catch (AuthenticationException e) {

        }

    }
}
