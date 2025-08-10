package org.casino.repositories;

import org.casino.associativeEntities.GamePlayer;
import org.casino.gameplay.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
    GamePlayer findByPlayerAndIsDone(Player player, boolean done);
}
