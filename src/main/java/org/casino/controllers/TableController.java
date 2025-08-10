package org.casino.controllers;

import org.casino.sevices.PlayerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class TableController {

    public final PlayerService playerService;

    public TableController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public String getTable(@PathVariable Long id, Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("username", user.getFullName());
        model.addAttribute("balance", playerService.findPlayerByKeycloak(user.getSubject()).getBalance());
        model.addAttribute("tableId", id);
        return "table"; // your HTML template name
    }


}
