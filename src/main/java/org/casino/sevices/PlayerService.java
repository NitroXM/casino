package org.casino.sevices;

import org.casino.gameplay.Player;
import org.casino.repositories.PlayerRepository;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findPlayerByKeycloak(String keycloakId) {
        return playerRepository.findByKeycloakId(keycloakId);
    }
}
