package org.casino.components.DTOs;

import org.casino.gameplay.Game;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Game}
 */
public record GameDto(String gameId, org.casino.components.DTOs.PlayerDto currentPlayer, List<org.casino.components.DTOs.PlayerDto> participants,
                      boolean isFinished) implements Serializable {
}