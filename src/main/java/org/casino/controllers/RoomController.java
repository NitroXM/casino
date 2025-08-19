package org.casino.controllers;

import org.casino.associativeEntities.GamePlayer;
import org.casino.gameplay.Game;
import org.casino.gameplay.Player;
import org.casino.gameplay.Room;
import org.casino.sevices.GamePlayerService;
import org.casino.sevices.GameService;
import org.casino.sevices.PlayerService;
import org.casino.sevices.RoomService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {

    private final PlayerService playerService;
    private final GameService gameService;
    private final RoomService roomService;
    private final GamePlayerService gamePlayerService;


    public RoomController(PlayerService playerService, GameService gameService, RoomService roomService, GamePlayerService gamePlayerService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.roomService = roomService;
        this.gamePlayerService = gamePlayerService;
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable Long id, Model model, @AuthenticationPrincipal OidcUser user) {

        Player player = playerService.findPlayerByKeycloak(user.getSubject());
        Room room = roomService.findById(id);
        Game PlayerCurrGame = gamePlayerService.activeGamePlayer(player)
                .map(GamePlayer::getGame)
                .orElse(null);
        Game gameInRoom = gameService.activeGame(room);
        String[] reasons = new String[2];

        if(PlayerCurrGame != null && gameInRoom != PlayerCurrGame) {
            reasons[0] = "You already have an active game.";
        }

        if(room.getPlayers().size() > 5) {
            reasons[1] = "The table is full.";
        }

        model.addAttribute("username", player.getGivenName());
        model.addAttribute("balance", player.getBalance());
        model.addAttribute("tableId", id);
        return "room";
    }
}
