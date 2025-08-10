package org.casino.components.mappers;

import org.casino.associativeEntities.GamePlayer;
import org.casino.components.DTOs.GameDto;
import org.casino.components.DTOs.PlayerDto;
import org.casino.gameplay.Game;
import org.casino.gameplay.Player;
import org.casino.repositories.CardRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameMapper {

    private final CardRepository cardRepository;
    public GameMapper(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private PlayerDto toDto(GamePlayer gp) {
        Player player = gp.getPlayer();
        return new PlayerDto(player.getGivenName(), gp.getSeatNumber(), gp.getBet(), cardRepository.findByOwner(gp));
    }

    private List<PlayerDto> toDto(List<GamePlayer> gps) {
        List<PlayerDto> dtos = new ArrayList<>();
        gps.forEach(gp -> dtos.add(toDto(gp)));
        return dtos;
    }

    //String nickname, int seatNumber, int bet, ArrayList<Card> cards

    public GameDto toDto(Game game) {

        return new GameDto(
                game.getGameId(),
                toDto(game.getCurrentPlayer()),
                toDto(game.getParticipants()),
                game.isFinished()
        );

        //String gameId, GamePlayer currentPlayer, List<GamePlayer> participants,
        //                      boolean isFinished
    }
}
