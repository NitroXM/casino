package org.casino.repositories;

import org.casino.associativeEntities.GamePlayer;
import org.casino.gameplay.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByGameId(String gameId);
    Game findByIsFinishedFalseAndParticipants(GamePlayer gamePlayer);
}
