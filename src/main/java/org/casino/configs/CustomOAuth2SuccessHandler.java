package org.casino.configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.casino.gameplay.Player;
import org.casino.repositories.PlayerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final PlayerRepository playerRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();
        String id = oidcUser.getSubject();
        String givenName = oidcUser.getGivenName();

        // Check if user already exists in DB
        if (!playerRepository.existsByKeycloakId(id)) {
            Player newPlayer = new Player(id, email, givenName);
            playerRepository.save(newPlayer);
        }

        // Continue with default flow
        response.sendRedirect("/");
    }
}

