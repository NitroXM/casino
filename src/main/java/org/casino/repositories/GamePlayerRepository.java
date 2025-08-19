package org.casino.repositories;

import org.casino.associativeEntities.GamePlayer;
import org.casino.gameplay.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
    Optional<GamePlayer> findByPlayerAndIsDoneFalse(Player player);
}
