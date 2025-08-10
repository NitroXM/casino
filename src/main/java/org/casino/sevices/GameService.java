package org.casino.sevices;

import org.casino.gameplay.Game;
import org.casino.gameplay.Player;
import org.casino.repositories.GamePlayerRepository;
import org.casino.repositories.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;

    public GameService(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
    }

    public Game activeGame(Player player) throws NullPointerException {
        return gameRepository.findByIsFinishedFalseAndParticipants(gamePlayerRepository.findByPlayerAndIsDone(player, false));
    }
}
