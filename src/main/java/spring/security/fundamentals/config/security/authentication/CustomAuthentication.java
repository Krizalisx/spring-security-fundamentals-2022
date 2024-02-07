package spring.security.fundamentals.config.security.authentication;

import java.util.Collection;
import javax.security.auth.Subject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public record CustomAuthentication(boolean isAuthenticated, String key) implements Authentication {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
