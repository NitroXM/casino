package org.casino.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.casino.components.DTOs.GameDto;
import org.casino.components.mappers.GameMapper;
import org.casino.gameplay.Game;
import org.casino.gameplay.Player;
import org.casino.sevices.GameService;
import org.casino.sevices.PlayerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final GameMapper gameMapper;

    public DataController(PlayerService playerService, GameService gameService, GameMapper gameMapper) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping("/game-state")
    public GameDto gameStatus(HttpServletRequest request, @AuthenticationPrincipal OidcUser user) {
        Player player = playerService.findPlayerByKeycloak(user.getSubject());
            Game game = gameService.activeGame(player);
            return gameMapper.toDto(game);
    }
}
