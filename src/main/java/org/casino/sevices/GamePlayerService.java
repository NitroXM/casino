package org.casino.sevices;

import org.casino.associativeEntities.GamePlayer;
import org.casino.gameplay.Player;
import org.casino.repositories.GamePlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayerService(GamePlayerRepository gamePlayerRepository) {
        this.gamePlayerRepository = gamePlayerRepository;
    }

    public Optional<GamePlayer> activeGamePlayer(Player player) throws NullPointerException {
        return gamePlayerRepository.findByPlayerAndIsDoneFalse(player);
    }
}
