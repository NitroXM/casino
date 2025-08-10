package org.casino.controllers;

import org.casino.sevices.PlayerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final PlayerService playerService;

    public HomeController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping("")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        model.addAttribute("username", user.getFullName());
    model.addAttribute("balance", playerService.findPlayerByKeycloak(user.getSubject()).getBalance());
        return "index";
    }
}
