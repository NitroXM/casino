package org.casino.repositories;

import org.casino.gameplay.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByKeycloakId(String id);
    Player findByKeycloakId(String keycloakId);
}
