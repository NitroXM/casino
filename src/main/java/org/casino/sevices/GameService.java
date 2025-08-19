package org.casino.sevices;

import org.casino.gameplay.Game;
import org.casino.gameplay.Room;
import org.casino.repositories.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game activeGame(Room room) {
        return gameRepository.findFirstByRoomAndIsFinishedFalse(room);
    }
}
