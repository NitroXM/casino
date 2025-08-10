package org.casino.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @GetMapping("/toLogout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       @AuthenticationPrincipal OidcUser user) throws Exception {

        if (user == null) {
            System.out.println("User is null. Cannot log out properly.");
            response.sendRedirect("/"); // fallback
            return;
        }

        String idToken = user.getIdToken().getTokenValue();
        String issuer = String.valueOf(user.getIssuer());

        String logoutUrl = issuer + "/protocol/openid-connect/logout" +
                "?id_token_hint=" + idToken +
                "&post_logout_redirect_uri=http://localhost:8081";

        // Invalidate AFTER мы получили всю нужную информацию
        request.getSession().invalidate();

        response.sendRedirect(logoutUrl);
    }
}
