package org.casino.repositories;

import org.casino.gameplay.Game;
import org.casino.gameplay.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByGameId(String gameId);
    Game findFirstByRoomAndIsFinishedFalse(Room room);
}
