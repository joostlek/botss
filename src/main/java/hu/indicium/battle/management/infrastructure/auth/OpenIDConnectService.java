package hu.indicium.battle.management.infrastructure.auth;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class OpenIDConnectService implements AuthService {

    private final KeycloakProvider keycloakProvider;

    public OpenIDConnectService(KeycloakProvider keycloakProvider) {
        this.keycloakProvider = keycloakProvider;
    }

    @Override
    public AuthUser getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return new AuthUser((Jwt) authentication.getPrincipal());
    }
}